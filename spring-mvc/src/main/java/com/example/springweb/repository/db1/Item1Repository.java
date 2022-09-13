package com.example.springweb.repository.db1;

import com.example.springweb.domain.db1.Item1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Item1Repository extends JpaRepository<Item1 , Long> {
}
