package com.spring.springsecurityjpa.service;

import com.spring.springsecurityjpa.model.MyUserDetails;
import com.spring.springsecurityjpa.model.User;
import com.spring.springsecurityjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUserName(userName);
        return userOpt.map(MyUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("Not Found: "+ userName));
    }
}
