package com.example.demo.repository.support;

import com.example.demo.domain.projection.DocumentInfo;
import com.example.demo.repository.support.boxbuilder.BoxBuilderFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentCustomRepository {

  Page<DocumentInfo> findByBoxAction(Pageable pageable, BoxBuilderFactory factory);
}
