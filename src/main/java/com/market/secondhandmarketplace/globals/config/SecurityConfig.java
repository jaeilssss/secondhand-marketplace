package com.market.secondhandmarketplace.globals.config;

import com.market.secondhandmarketplace.globals.jwt.JwtAuthenticationFilter;
import com.market.secondhandmarketplace.globals.jwt.JwtProviders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProviders jwtProviders;

    public SecurityConfig(JwtProviders jwtProviders) {
        this.jwtProviders = jwtProviders;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .rememberMe(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/member/login").permitAll();
                    request.requestMatchers("/api/member/renew/refresh").permitAll();
                    request.anyRequest().permitAll();
                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtProviders), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
