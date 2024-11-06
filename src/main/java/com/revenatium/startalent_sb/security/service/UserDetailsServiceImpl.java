package com.revenatium.startalent_sb.security.service;

import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = (User) userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario" + username));


        Collection<? extends GrantedAuthority> authorities = user.getUserRoles()
                .stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_".concat(userRole.getRole().getName().name())))
                .collect(Collectors.toSet());


        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPasswordHash(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
