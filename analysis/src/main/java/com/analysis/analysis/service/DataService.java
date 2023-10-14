package com.analysis.analysis.service;


import com.analysis.analysis.model.Data;
import com.analysis.analysis.repository.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService  {
    @Autowired

    private IDataRepository dataRepository;
    public List<Data> findAll() {
        return dataRepository.findAll();
    }
}