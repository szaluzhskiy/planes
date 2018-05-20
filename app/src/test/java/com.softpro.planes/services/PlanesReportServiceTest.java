package com.softpro.planes.services;

import com.softpro.planes.config.ProjectProperties;
import com.softpro.planes.repository.PlaneReportHolderRepository;
import com.softpro.planes.services.exception.ServiceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Stanislav_Zaluzhskii on 5/17/2018.
 */

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(ProjectProperties.BASE_PACKAGE)
public class PlanesReportServiceTest {

    @MockBean
    private PlaneReportHolderRepository repository;

    @Autowired
    private PlanesReportService planesReportService;

    @Test(expected = ServiceException.class)
    public void testWrongDatesException() {
        when(repository.buildPlaneLandingsReport(anyObject(), anyObject(), anyString())).thenReturn(new ArrayList());

        LocalDate startDate = LocalDate.of(2018, Month.MAY, 5);
        LocalDate endDate = LocalDate.of(2018, Month.MAY, 1);
        String airport = "СП";

        planesReportService.buildPlaneLandingsReport(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), airport);
        fail("Should throw exception.");
    }
}
