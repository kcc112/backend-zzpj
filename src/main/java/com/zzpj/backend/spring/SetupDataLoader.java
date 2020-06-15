package com.zzpj.backend.spring;

import com.zzpj.backend.entities.*;
import com.zzpj.backend.repositories.*;
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

    private UserRepository userRepository;

    private WarehouseRepository warehouseRepository;

    private RoleRepository roleRepository;

    private PrivilegeRepository privilegeRepository;

    private AlcoholRepository alcoholRepository;

    private PurchaseRepository purchaseRepository;

    private PurchaseListRepository purchaseListRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(UserRepository userRepository, WarehouseRepository warehouseRepository,
                           RoleRepository roleRepository, PrivilegeRepository privilegeRepository,
                           AlcoholRepository alcoholRepository, PurchaseRepository purchaseRepository,
                           PurchaseListRepository purchaseListRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.alcoholRepository = alcoholRepository;
        this.purchaseRepository = purchaseRepository;
        this.purchaseListRepository = purchaseListRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) return;

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        final List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        final List<Privilege> userPrivileges = new ArrayList<Privilege>(Arrays.asList(readPrivilege));

        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        Role userRole = createRoleIfNotFound("ROLE_USER", userPrivileges);

        createUserIfNotFound("admin@edu.pl", "Mateusz", "Wasilewski", "admin", new ArrayList<Role>(Arrays.asList(adminRole)));
        User user = createUserIfNotFound("user@edu.pl", "Szymon", "Dobrowolski", "user", new ArrayList<Role>(Arrays.asList(userRole)));

        Warehouse warehouse = createWarehouse(10);

        Alcohol taterka = createAlcoholIfNotFound("Tatra", 1.99, warehouse);

        Purchase purchase = createPurchase(user);

        createPurchaseListIfNotFound(taterka, 3, purchase);


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
            user.setPassword(passwordEncoder.encode(password));
            user.setLogin(login);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }

    @Transactional
    Warehouse createWarehouse(final int amount) {
        Warehouse warehouse = new Warehouse();
        warehouse.setAmount(amount);
        warehouseRepository.save(warehouse);
        return warehouse;
    }


    @Transactional
    Alcohol createAlcoholIfNotFound(final String name, final double cost, final Warehouse warehouse) {
        Alcohol alcohol = alcoholRepository.findByName(name);
        if (alcohol == null) {
            alcohol = new Alcohol();
            alcohol.setName(name);
            alcohol.setCost(cost);
            alcohol.setWarehouse(warehouse);
        }
        alcoholRepository.save(alcohol);
        return alcohol;

    }

    @Transactional
    Purchase createPurchase(final User user) {
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchaseRepository.save(purchase);
        return purchase;

    }

    @Transactional
    PurchaseList createPurchaseListIfNotFound(final Alcohol alcohol, final int amount, final Purchase purchase) {
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.setAlcohol(alcohol);
        purchaseList.setBuyAmount(amount);
        purchaseList.setPurchase(purchase);
        purchaseListRepository.save(purchaseList);
        return purchaseList;

    }



}
