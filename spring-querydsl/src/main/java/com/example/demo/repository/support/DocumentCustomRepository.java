package com.example.demo.repository.support;

import com.example.demo.domain.projection.DocumentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentCustomRepository {
  Page<DocumentProjection> findAllQuery(Pageable pageable, Long userId);

  Page<DocumentProjection> findByAllQuery2(Pageable pageable);
}
