package com.collectdata.collect.repository;

import com.collectdata.collect.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDataRepository extends JpaRepository<Data, Integer> {
}
