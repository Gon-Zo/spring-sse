package com.example.springtransaction.service.dto;

import com.example.springtransaction.domain.Product;
import com.example.springtransaction.domain.ProductUser;
import com.example.springtransaction.domain.ProductUserKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InitDTO {

  private Long productId;

  private Integer amount;

  public ProductUser toEntity(Long userId, Product product) {
    ProductUserKey id = ProductUserKey.builder().productId(productId).userId(userId).build();
    return ProductUser.initBuilder().id(id).amount(this.amount).product(product).build();
  }
}
