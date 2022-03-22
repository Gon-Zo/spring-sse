package com.example.demo.repository;

import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.PaymentCommentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCommentRepository
    extends JpaRepository<PaymentComment, PaymentCommentKey> {}
