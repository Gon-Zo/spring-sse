package com.example.app.repository;

import com.example.app.domain.user.UserCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCertificationRepository extends JpaRepository<UserCertification, Long> {
}
