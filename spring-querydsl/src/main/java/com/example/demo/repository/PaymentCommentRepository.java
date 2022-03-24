package com.example.demo.repository;

import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.PaymentCommentKey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentCommentRepository extends JpaRepository<PaymentComment, PaymentCommentKey> {

  @EntityGraph(attributePaths = {"document"})
  List<PaymentComment> findById_DocumentId(Long documentId);
}
