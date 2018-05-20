package com.softpro.planes.repository;

import com.softpro.planes.config.ProjectProperties;
import com.softpro.planes.models.PlaneReportHolder;
import com.softpro.planes.repository.PlaneReportHolderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(ProjectProperties.BASE_PACKAGE)
@EntityScan( basePackages = {ProjectProperties.MODELS_PACKAGE} )
@EnableTransactionManagement
public class PlaneReportHolderRepositoryTest {

    @Autowired
    private PlaneReportHolderRepository planeReportHolderRepository;

    @Test
    @Transactional
    public void shouldCalculateNumberOfLandingsForWeek() {
        LocalDate  startDate = LocalDate.of(2018, Month.MAY, 7);
        LocalDate  endDate = LocalDate.of(2018, Month.MAY, 13);
        String airport = "СП";

        List<PlaneReportHolder> res = planeReportHolderRepository.buildPlaneLandingsReport(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), airport);
        assertNotNull(res);
        assertEquals(3, res.size());
        assertTrue(res.stream().allMatch(planeReportHolder -> airport.equals(planeReportHolder.getAirport())));
    }

    @Test
    @Transactional
    public void shouldCalculateNumberOfLandingsForMonth() {
        LocalDate  startDate = LocalDate.of(2018, Month.MAY, 1);
        LocalDate  endDate = LocalDate.of(2018, Month.MAY, 31);
        String airport = "СП";

        List<PlaneReportHolder> res = planeReportHolderRepository.buildPlaneLandingsReport(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), airport);
        assertNotNull(res);
        assertEquals(3, res.size());
        assertTrue(res.stream().allMatch(planeReportHolder -> airport.equals(planeReportHolder.getAirport())));
        assertTrue(res.stream().filter(planeReportHolder -> "Boeing 747-400-2".equals(planeReportHolder.getPlane())).findFirst().get().getNumOfLandings() == 31);
    }

    @Test
    @Transactional
    public void shouldCalculateZeroNumberOfLandingsForWrongAirport() {
        LocalDate  startDate = LocalDate.of(2018, Month.MAY, 1);
        LocalDate  endDate = LocalDate.of(2018, Month.MAY, 31);
        String airport = "ЕЕЕ";

        List<PlaneReportHolder> res = planeReportHolderRepository.buildPlaneLandingsReport(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), airport);
        assertNotNull(res);
        assertEquals(0, res.size());
    }
}
