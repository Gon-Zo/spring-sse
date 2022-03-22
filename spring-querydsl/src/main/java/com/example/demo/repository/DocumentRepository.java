package com.example.demo.repository;

import com.example.demo.domain.Document;
import com.example.demo.repository.support.DocumentCustomRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository
    extends JpaRepository<Document, Long>, DocumentCustomRepository {

  <T> Optional<T> findById(Long id, Class<T> type);

  @EntityGraph(attributePaths = {"paymentCommentSet"})
  <T> Optional<T> findByUser_Id(Long userId, Class<T> type);
}
