package ru.cashflow.cashflow.rest.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.googleapis.util.Utils;

import ru.cashflow.cashflow.JwtUtil;
import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.services.UserService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class AuthRestController {

    @Value("${google.client-id}")
    private String clientId;

    private final UserService userService;

    public AuthRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody String idTokenString) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new GooglePublicKeysManager.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory())
                        .setPublicCertsEncodedUrl("https://www.googleapis.com/oauth2/v1/certs").build())
                .setAudience(Collections.singletonList(clientId))
                .build();

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                // Получаем информацию о пользователе из токена
                String userId = payload.getSubject();
                String email = payload.getEmail();
                String name = (String) payload.get("name");

                // Сохраняем или обновляем пользователя в базе данных
                User userEntity = userService.findUserByEmail(email).orElse(new User());
                userEntity.setEmail(email);
                userEntity.setName(name);
                userService.saveUser(userEntity);

                // Создаем JWT токен
                String jwtToken = JwtUtil.generateToken(userEntity);

                // Отправляем JWT токен клиенту
                return ResponseEntity.ok(Collections.singletonMap("token", jwtToken));
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
