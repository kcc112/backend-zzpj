package com.zzpj.backend.security;

import com.zzpj.backend.entities.Privilege;
import com.zzpj.backend.entities.Role;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.repositories.RoleRepository;
import com.zzpj.backend.repositories.UserRepository;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
           throw new UsernameNotFoundException("No user found with username: " + login);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(),
                true, true, true, true, getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .forEach(authorities::add);
        }

        return authorities;
    }
}
