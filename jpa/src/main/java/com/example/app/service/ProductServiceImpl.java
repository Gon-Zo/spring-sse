package com.example.app.service;

import com.example.app.constract.State;
import com.example.app.domain.Product;
import com.example.app.domain.ProductUser;
import com.example.app.repository.ProductRepository;
import com.example.app.repository.ProductUserRepository;
import com.example.app.service.dto.InitDTO;
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
