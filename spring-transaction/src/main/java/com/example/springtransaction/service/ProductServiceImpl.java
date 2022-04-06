package com.example.springtransaction.service;

import com.example.springtransaction.constract.State;
import com.example.springtransaction.domain.Product;
import com.example.springtransaction.domain.ProductUser;
import com.example.springtransaction.repository.ProductRepository;
import com.example.springtransaction.repository.ProductUserRepository;
import com.example.springtransaction.service.dto.InitDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service("productService")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final ProductUserRepository productUserRepository;

  @Override
  @Retryable(
      maxAttempts = 3,
      backoff = @Backoff(delay = 2000),
      value = {ObjectOptimisticLockingFailureException.class})
  public State initProductByUser(InitDTO dto, Long userId) {

    Product product = productRepository.findById(dto.getProductId()).orElseThrow();

    if (State.PROGRESS.equals(product.getState())) {

      Integer total = product.getTotalAmount();

      product.upCurrentAmount(dto.getAmount());

      Integer current = product.getCurrentAmount();

      if (current >= total) {
        product.soldOutState();
      }

      productRepository.flush();

      ProductUser productUser = dto.toEntity(userId, product);

      productUserRepository.save(productUser);
    }

    return product.getState();
  }
}
