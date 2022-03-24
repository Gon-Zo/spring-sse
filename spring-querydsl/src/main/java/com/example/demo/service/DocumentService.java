package com.example.demo.service;

import com.example.demo.domain.Document;
import com.example.demo.domain.User;
import com.example.demo.domain.projection.DocumentInfo;
import com.example.demo.service.dto.DocumentBoxDTO;
import com.example.demo.service.dto.DocumentDTO;
import org.springframework.data.domain.Page;

public interface DocumentService {

  Document createdDocument(DocumentDTO dto, User user);

  Page<DocumentInfo> getDocumentBoxList(DocumentBoxDTO dto, User user);
}
