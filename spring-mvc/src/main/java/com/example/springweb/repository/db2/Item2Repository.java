package com.example.springweb.repository.db2;

import com.example.springweb.domain.db2.Item2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Item2Repository extends JpaRepository<Item2 , Long> {
}
