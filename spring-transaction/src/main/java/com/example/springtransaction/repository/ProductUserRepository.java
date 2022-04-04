package com.example.springtransaction.repository;

import com.example.springtransaction.domain.ProductUser;
import com.example.springtransaction.domain.ProductUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUserRepository extends JpaRepository<ProductUser, ProductUserKey> {}
