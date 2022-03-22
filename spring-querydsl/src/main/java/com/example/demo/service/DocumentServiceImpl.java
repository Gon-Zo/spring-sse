package com.example.demo.service;

import com.example.demo.domain.Document;
import com.example.demo.domain.User;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.dto.DocumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

  private final DocumentRepository documentRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Document createdDocument(DocumentDTO dto, User user) {

    Document entity = dto.toEntity(user);

    documentRepository.save(entity);

    return entity;
  }
}
