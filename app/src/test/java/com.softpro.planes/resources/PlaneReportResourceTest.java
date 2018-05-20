package com.softpro.planes.resources;

import com.softpro.planes.config.ProjectProperties;
import com.softpro.planes.dto.PlaneReportDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by Stanislav_Zaluzhskii on 5/17/2018.
 */
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(ProjectProperties.BASE_PACKAGE)
public class PlaneReportResourceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnDailyReport() {
        String url = "http://localhost:" + this.port;
        String airport = "МД";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).path("/reports/airport/plane")
                .queryParam("startDate", "2018-05-07T00:00:00Z")
                .queryParam("endDate", "2018-05-21T00:00:00Z")
                .queryParam("airport", airport);

        ResponseEntity<List<PlaneReportDto>> response = testRestTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PlaneReportDto>>() {
                });
        assertNotNull(response.getBody());
        assertEquals(4, response.getBody().size());
        assertTrue(response.getBody().stream().allMatch(planeReportHolder -> airport.equals(planeReportHolder.getAirport())));
        assertTrue(response.getBody().stream().filter(planeReportDto -> "Airbus A380-800".equals(planeReportDto.getPlane())).findFirst().get().getNumOfLandings() == 2);
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
