package com.example.springweb.repository;

import com.example.springweb.domain.CurrentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentRepository extends JpaRepository<CurrentDate, Long> {}
