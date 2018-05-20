package com.softpro.planes.repository;

import com.softpro.planes.models.PlaneReportHolder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.transform.Transformers;

/**
 * Created by Stanislav_Zaluzhskii on 5/16/2018.
 */
@Component
public class PlaneReportHolderRepository {

    @Autowired
    private EntityManager entityManager;

    public List<PlaneReportHolder> buildPlaneLandingsReport(Date startDate, Date endDate, String airport) {
        Session session = entityManager.unwrap( Session.class );
        List<PlaneReportHolder> results = new ArrayList<>();
        session.doWork( connection -> {
            try (CallableStatement function = connection
                    .prepareCall(
                            "{ call planes.calc_landing_number_by_period(?, ?, ?) }"
                    )
            ) {
                function.setDate(1, new java.sql.Date(startDate.getTime()));
                function.setDate(2, new java.sql.Date(endDate.getTime()));
                function.setString(3, airport);

                function.execute();
                ResultSet rs =  function.getResultSet();
                while (rs.next()) {
                    PlaneReportHolder prh = new PlaneReportHolder();
                    prh.setAirport(rs.getString(1));
                    prh.setPlane(rs.getString(2));
                    prh.setNumOfLandings(rs.getInt(3));
                    results.add(prh);
                }
            }
        } );
        return  results;
    }
}
