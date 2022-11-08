package com.spring.security.authentication.filters;

import com.spring.security.authentication.OtpAuthentication;
import com.spring.security.authentication.UsernamePasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");

        if (code == null) {
            Authentication authentication = new UsernamePasswordAuthentication(username, password);
            manager.authenticate(authentication);
        } else {
            Authentication authentication = new OtpAuthentication(username, code);
            manager.authenticate(authentication);

            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

            Map<String,String> body = new HashMap<>();
            body.put("username",username);

            String jwt = Jwts.builder()
                    .setClaims(body)
                    .signWith(key)
                    .compact();
            response.setHeader("Authorization", jwt);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        //filter request if it is login
        return !request.getServletPath().equals("/login");
    }
}
