package ru.cashflow.cashflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public UserDetailsService userDetailsService(UserService userService) {
    // // return username -> {
    // // Optional<User> user = userService.findUserByName(username);
    // // if (user.isPresent())
    // // return user.get();
    // // throw new UsernameNotFoundException("User ‘" + username + "’ not found");
    // // };
    // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    // manager.createUser(User.withUsername("user")
    // .password(passwordEncoder().encode("password"))
    // .roles("USER")
    // .build());
    // return manager;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/", "/login", "/error").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(customOAuth2UserService))
                        .defaultSuccessUrl("/user-groups")
                        .failureUrl("/error"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll());
        return http.build();
    }

}
