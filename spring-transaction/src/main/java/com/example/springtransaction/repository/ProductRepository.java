package com.example.springtransaction.repository;

import com.example.springtransaction.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Override
  @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "30000")})
  @Lock(value = LockModeType.PESSIMISTIC_FORCE_INCREMENT)
  Optional<Product> findById(Long aLong);
}
