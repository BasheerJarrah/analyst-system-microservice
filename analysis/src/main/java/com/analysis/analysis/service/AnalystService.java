package com.analysis.analysis.service;


import com.analysis.analysis.model.Analyst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnalystService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Analyst> findAll() {
        return mongoTemplate.findAll(Analyst.class, "Analyst");
    }

    public void updateDocument(Analyst analyst) {
        System.out.println("WE ARE UPDATING");
        mongoTemplate.remove(new Query(), "Analyst");
        mongoTemplate.save(analyst, "Analyst");
        //Update update = new Update().set("average", analyst.getAverage()).set("maximum", analyst.getMaximum()).set("minimum", analyst.getMinimum());
        //mongoTemplate.updateMulti(new Query(), update, Analyst.class, "Analyst");
        //mongoTemplate.updateMulti(new Query(), update, Analyst.class,"Analyst");
    }
}
