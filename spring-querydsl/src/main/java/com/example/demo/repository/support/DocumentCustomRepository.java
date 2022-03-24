package com.example.demo.repository.support;

import com.example.demo.domain.projection.DocumentInfo;
import com.example.demo.repository.support.boxaction.BoxActionFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentCustomRepository {

  Page<DocumentInfo> findByBoxAction(Pageable pageable, BoxActionFactory factory);
}
