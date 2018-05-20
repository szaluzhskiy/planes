package com.softpro.planes.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class PlaneReportHolder {
    private String airport;
    private String plane;
    private Integer numOfLandings;
}
