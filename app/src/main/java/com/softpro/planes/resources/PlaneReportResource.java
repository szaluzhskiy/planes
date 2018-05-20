package com.softpro.planes.resources;

import com.softpro.planes.dto.PlaneReportDto;
import com.softpro.planes.models.PlaneReportHolder;
import com.softpro.planes.services.PlanesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.glasnost.orika.MapperFacade;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/reports")
public class PlaneReportResource {

    @Autowired
    private PlanesReportService planesReportService;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @RequestMapping(value = "/airport/plane", method = RequestMethod.GET)
    public List<PlaneReportDto> getPlaneReport(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate, @RequestParam("airport") String airport) {
        List<PlaneReportHolder> result = planesReportService.buildPlaneLandingsReport(startDate, endDate, airport);

        return orikaMapperFacade.mapAsList(result, PlaneReportDto.class);
    }
}
