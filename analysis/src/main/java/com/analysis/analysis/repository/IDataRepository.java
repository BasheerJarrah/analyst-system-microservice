package com.analysis.analysis.repository;

import com.analysis.analysis.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDataRepository extends JpaRepository<Data,Integer> {
}
