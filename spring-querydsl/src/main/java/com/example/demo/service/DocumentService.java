package com.example.demo.service;

import com.example.demo.domain.Document;
import com.example.demo.domain.User;
import com.example.demo.service.dto.DocumentDTO;

public interface DocumentService {
    Document createdDocument(DocumentDTO dto , User user);
}
