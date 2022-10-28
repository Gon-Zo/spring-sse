package com.example.app.repository.user;

import com.example.app.domain.user.UserLatestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLatestStateRepository extends JpaRepository<UserLatestState, Long> {
}
