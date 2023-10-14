package com.analysis.analysis.controller;

import com.analysis.analysis.model.Analyst;
import com.analysis.analysis.model.Data;
import com.analysis.analysis.service.AnalystService;
import com.analysis.analysis.service.DataService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analysis")
@CrossOrigin(origins = {"http://localhost:8080"})
public class AnalysisController {
    @Autowired
    private DataService dataService;
    @Autowired
    private AnalystService analystService;

    @GetMapping("/analyze")
    public String analyze() {
        List<Data> dataList = dataService.findAll();
        Analyst analyst = new Analyst();
        analyst.setAverage(dataList.stream().mapToDouble(Data::getVal).average().getAsDouble());
        analyst.setMaximum(dataList.stream().mapToDouble(Data::getVal).max().getAsDouble());
        analyst.setMinimum(dataList.stream().mapToDouble(Data::getVal).min().getAsDouble());
        analystService.updateDocument(analyst);
        return "DONE";
    }
}
