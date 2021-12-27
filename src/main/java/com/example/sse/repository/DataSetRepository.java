package com.example.sse.repository;

import com.example.sse.doamin.DataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DataSetRepository extends JpaRepository<DataSet , Long> {

    @Override
    @Transactional(readOnly = true)
    List<DataSet> findAll();

}
