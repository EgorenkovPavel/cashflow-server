package ru.cashflow.cashflow;

import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        User userEntity = userService.saveOrUpdateUser(oAuth2User);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "email");

        // return new org.springframework.security.core.userdetails.User(
        // userEntity.getEmail(),
        // "",
        // Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        // );
    }
}
