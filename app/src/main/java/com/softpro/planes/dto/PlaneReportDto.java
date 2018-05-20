package com.softpro.planes.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * DTO for result of plane report.
 */
@Data
@Accessors(chain = true)
public class PlaneReportDto {
    private String airport;
    private String plane;
    private Integer numOfLandings;
}
