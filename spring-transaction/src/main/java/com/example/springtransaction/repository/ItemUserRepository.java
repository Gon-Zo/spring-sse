package com.example.springtransaction.repository;

import com.example.springtransaction.domain.ItemUser;
import com.example.springtransaction.domain.ItemUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemUserRepository extends JpaRepository<ItemUser, ItemUserKey> {}
