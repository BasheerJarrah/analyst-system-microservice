package com.collectdata.collect.service;

import com.collectdata.collect.model.Data;
import com.collectdata.collect.repository.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService implements IDataService {
    private final IDataRepository dataRepository;
    @Autowired
    public DataService(IDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public List<Data> findAll() {
        return dataRepository.findAll();
    }

    @Override
    public void saveData(Data data){
        this.dataRepository.save(data);
    }

}
