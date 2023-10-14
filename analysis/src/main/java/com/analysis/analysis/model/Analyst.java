package com.analysis.analysis.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class Analyst {
    @Id
    private String id;
    private double average;
    private double maximum;
    private double minimum;
}
