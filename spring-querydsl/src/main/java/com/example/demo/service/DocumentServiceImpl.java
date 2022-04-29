package com.example.demo.service;

import com.example.demo.domain.Document;
import com.example.demo.domain.User;
import com.example.demo.domain.projection.DocumentInfo;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.support.boxbuilder.BoxBuilderFactory;
import com.example.demo.service.dto.DocumentBoxDTO;
import com.example.demo.service.dto.DocumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    entity.getPaymentCommentSet().addAll(dto.getPaymentCommentList(entity.getId()));

    return entity;
  }

  @Override
  @Transactional(rollbackFor = Exception.class, readOnly = true)
  public Page<DocumentInfo> getDocumentBoxList(DocumentBoxDTO dto, User user) {

    PageRequest pageable = PageRequest.of(dto.getPage(), dto.getSize());

    BoxBuilderFactory factory = new BoxBuilderFactory().getBoxAction(dto.getType(), user.getId());

    return documentRepository.findByBoxAction(pageable, factory);
  }
}
