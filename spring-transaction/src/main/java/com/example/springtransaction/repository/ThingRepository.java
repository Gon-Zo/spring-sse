package com.example.springtransaction.repository;

import com.example.springtransaction.domain.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingRepository extends JpaRepository<Thing, Long> {}
