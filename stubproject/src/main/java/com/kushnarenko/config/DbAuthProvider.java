package com.kushnarenko.config;

import com.kushnarenko.model.User;
import com.kushnarenko.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DbAuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findByUsername(name);

        if (Objects.nonNull(user)) {
            return getAuthentication(name, password, user);
        } else {
            throw new BadCredentialsException("Bad credentials");
        }

    }

    private Authentication getAuthentication(String name, String password, User user) {
        AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(name, password,
                AuthorityUtils.createAuthorityList(user.getRole().toString()));

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
