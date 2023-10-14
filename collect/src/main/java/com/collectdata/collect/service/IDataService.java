package com.collectdata.collect.service;

import com.collectdata.collect.model.*;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDataService {
    List<Data> findAll();
    void saveData(Data data);
}
