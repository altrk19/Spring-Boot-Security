package com.spring.security.service;

import com.spring.security.entity.User;
import com.spring.security.model.CustomUserDetails;
import com.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> exception =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User user = userRepository.findUserByUsername(username).orElseThrow(exception);
        return new CustomUserDetails(user);
    }
}
