package com.zzpj.backend.spring;

import com.zzpj.backend.entities.Privilege;
import com.zzpj.backend.entities.Role;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.repositories.PrivilegeRepository;
import com.zzpj.backend.repositories.RoleRepository;
import com.zzpj.backend.repositories.UserRepository;
import com.zzpj.backend.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;



    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(alreadySetup) return;

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        final List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        final List<Privilege> userPrivileges = new ArrayList<Privilege>(Arrays.asList(readPrivilege));

        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);

        createRoleIfNotFound("ROLE_USER", userPrivileges);



        createUserIfNotFound("test@test.com", "Test", "Test", "test", new ArrayList<Role>(Arrays.asList(adminRole)));

        alreadySetup = true;


    }

    @Transactional
    Privilege createPrivilegeIfNotFound(final String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String login, final String firstName, final String lastName, final String password, final Collection<Role> roles) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(HashUtils.sha256(password));
            user.setLogin(login);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }
}
