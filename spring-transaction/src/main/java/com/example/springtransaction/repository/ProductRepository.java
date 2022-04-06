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
  @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
  @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
  Optional<Product> findById(Long aLong);
}
