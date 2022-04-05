package com.example.springtransaction.service;

import com.example.springtransaction.constract.State;
import com.example.springtransaction.domain.Product;
import com.example.springtransaction.domain.ProductUser;
import com.example.springtransaction.repository.ProductRepository;
import com.example.springtransaction.repository.ProductUserRepository;
import com.example.springtransaction.service.dto.InitDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final ProductUserRepository productUserRepository;

  @SneakyThrows
  @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
  public State initProductByUser(InitDTO dto, Long userId) {

    long start = System.currentTimeMillis();

    Product product = productRepository.findById(dto.getProductId()).orElseThrow();

    log.info("[Thread Name]={},[user id]={}", Thread.currentThread().getName(), userId);

    log.info("[Time]={}ms", (System.currentTimeMillis() - start));

    if (State.PROGRESS.equals(product.getState())) {

//      ProductUser productUser = dto.toEntity(userId, product);
//
//      productUserRepository.save(productUser);

      Integer total = product.getTotalAmount();

      product.upCurrentAmount(dto.getAmount());

      log.info("[userId]={},[currentAmount]={}", userId, product.getCurrentAmount());

      Integer current = product.getCurrentAmount();

      if (current >= total) {
        product.soldOutState();
      }
    }

    return product.getState();
  }
}
