package com.softpro.planes.services;

import com.softpro.planes.models.PlaneReportHolder;
import com.softpro.planes.repository.PlaneReportHolderRepository;
import com.softpro.planes.services.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlanesReportService {

    @Autowired
    private PlaneReportHolderRepository planeReportHolderRepository;

    public List<PlaneReportHolder> buildPlaneLandingsReport(Date startDate, Date endDate, String airport) {
        if (endDate.before(startDate)) {
            throw new ServiceException(String.format("Date range is wrong. StartDate %s is grater then endDate %s ", startDate, endDate));
        }
        return planeReportHolderRepository.buildPlaneLandingsReport(startDate, endDate, airport);
    }
}
