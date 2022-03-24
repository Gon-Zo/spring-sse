package com.example.demo.service;

import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.User;
import com.example.demo.service.dto.LiquidatedPaymentDTO;

public interface PaymentCommentService {

  PaymentComment liquidateDocument(LiquidatedPaymentDTO dto, User user);
}
