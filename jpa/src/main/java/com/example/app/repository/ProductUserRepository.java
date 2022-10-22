package com.example.app.repository;

import com.example.app.domain.ProductUser;
import com.example.app.domain.ProductUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUserRepository extends JpaRepository<ProductUser, ProductUserKey> {}
