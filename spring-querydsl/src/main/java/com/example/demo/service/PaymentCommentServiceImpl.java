package com.example.demo.service;

import com.example.demo.config.exception.NoDataException;
import com.example.demo.domain.Document;
import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.User;
import com.example.demo.enums.MessageType;
import com.example.demo.enums.StateEnum;
import com.example.demo.repository.PaymentCommentRepository;
import com.example.demo.service.dto.LiquidatedPaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentCommentServiceImpl implements PaymentCommentService {

  private final PaymentCommentRepository paymentCommentRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public PaymentComment liquidateDocument(LiquidatedPaymentDTO dto, User user) {

    List<PaymentComment> paymentCommentList =
        paymentCommentRepository.findById_DocumentId(dto.getDocumentId());

    PaymentComment userPaymentComment =
        paymentCommentList.stream()
            .filter(paymentComment -> user.getId().equals(paymentComment.getId().getUserId()))
            .findFirst()
            .orElseThrow(() -> new NoDataException(MessageType.NoPaymentCommentUser));

    StateEnum state = dto.getState();

    userPaymentComment.updateCommentAndState(dto.getComment(), state);

    Document document = userPaymentComment.getDocument();

    if (StateEnum.NO.equals(state)) {
      document.refuseState();
      return userPaymentComment;
    }

    boolean isOk =
        paymentCommentList.stream()
            .allMatch(paymentComment -> StateEnum.OK.equals(paymentComment.getState()));

    if (isOk) {
      document.approveState();
    } else {
      document.proceedState();
    }

    return userPaymentComment;
  }
}
