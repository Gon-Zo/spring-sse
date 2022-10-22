package com.example.app.repository;

import com.example.app.domain.ItemUser;
import com.example.app.domain.ItemUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemUserRepository extends JpaRepository<ItemUser, ItemUserKey> {}
