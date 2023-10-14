package com.show.info.service;

import com.show.info.model.Analyst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnalystService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Analyst> findAll() {
        return mongoTemplate.findAll(Analyst.class, "Analyst");
    }
}
