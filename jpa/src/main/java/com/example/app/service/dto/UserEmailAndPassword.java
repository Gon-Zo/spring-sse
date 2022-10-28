package com.example.app.service.dto;

import org.springframework.beans.factory.annotation.Value;

public interface UserEmailAndPassword {

    Long getId();

    String getEmail();

    @Value("#{target.userPassword.password}")
    String getPassword();
}
