package com.example.app.web.user;

import com.example.app.service.dto.LoginDTO;
import com.example.app.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public LoginDTO.Response login(@RequestBody LoginDTO.Request dto) {
        return loginService.login(dto);
    }
}
