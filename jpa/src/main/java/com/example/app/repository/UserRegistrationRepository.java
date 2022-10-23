package com.example.app.repository;

import com.example.app.domain.user.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
}
