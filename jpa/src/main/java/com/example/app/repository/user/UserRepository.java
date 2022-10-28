package com.example.app.repository.user;

import com.example.app.constract.StatusType;
import com.example.app.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"userPassword"})
    <T> Optional<T> findByEmailAndStatus(String email, StatusType status, Class<T> type);
}
