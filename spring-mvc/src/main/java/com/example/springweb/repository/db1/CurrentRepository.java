package com.example.springweb.repository.db1;

import com.example.springweb.domain.db1.CurrentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentRepository extends JpaRepository<CurrentDate, Long> {}
