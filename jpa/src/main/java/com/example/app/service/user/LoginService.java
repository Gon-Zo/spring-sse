package com.example.app.service.user;

import com.example.app.constract.StatusType;
import com.example.app.domain.user.UserToken;
import com.example.app.repository.user.UserRepository;
import com.example.app.repository.user.UserTokenRepository;
import com.example.app.service.dto.LoginDTO;
import com.example.app.service.dto.UserEmailAndPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserTokenRepository userTokenRepository;

    @Transactional
    public LoginDTO.Response login(LoginDTO.Request dto) {

        UserEmailAndPassword user = userRepository.findByEmailAndStatus(dto.getEmail(), StatusType.JOIN, UserEmailAndPassword.class).orElseThrow(() -> new RuntimeException("not found user"));

        if (passwordEncoder.matches(user.getPassword(), dto.getPassword())) {

            String accessToken = "xxxx";

            UserToken userToken = UserToken.newUserToken(accessToken, null, LocalDateTime.now());

            userTokenRepository.save(userToken);

            return new LoginDTO.Response(accessToken);
        }

        throw new RuntimeException("BadRequestException User Not Matches");
    }
}
