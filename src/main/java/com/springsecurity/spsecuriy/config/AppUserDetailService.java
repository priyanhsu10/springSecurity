package com.springsecurity.spsecuriy.config;

import com.springsecurity.spsecuriy.entities.AppUser;
import com.springsecurity.spsecuriy.model.User;
import com.springsecurity.spsecuriy.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserDetailService implements UserDetailsService {
    private final UserRepository repository;

    public AppUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user= repository.findByUsername(s).
                orElseThrow(()->new UsernameNotFoundException("Invalid user"));
        return  new User(user);

    }
}
