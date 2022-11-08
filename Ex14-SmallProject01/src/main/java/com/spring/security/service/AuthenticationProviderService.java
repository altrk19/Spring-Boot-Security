package com.spring.security.service;

import com.spring.security.entity.enums.EncryptionAlgorithm;
import com.spring.security.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);
        if (EncryptionAlgorithm.BCRYPT.equals(userDetails.getUser().getAlgorithm())) {
            return checkPassword(userDetails, password, bCryptPasswordEncoder);
        } else if (EncryptionAlgorithm.SCRYPT.equals(userDetails.getUser().getAlgorithm())) {
            return checkPassword(userDetails, password, sCryptPasswordEncoder);
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
        if (encoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                    user.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
