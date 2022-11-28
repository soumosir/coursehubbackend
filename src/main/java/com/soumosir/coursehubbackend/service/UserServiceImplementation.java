package com.soumosir.coursehubbackend.service;


import com.soumosir.coursehubbackend.cache.ForgotPasswordService;
import com.soumosir.coursehubbackend.cache.LoginAttemptService;
import com.soumosir.coursehubbackend.model.AppUser;
import com.soumosir.coursehubbackend.model.Role;
import com.soumosir.coursehubbackend.exceptions.ResourceNotFoundException;
import com.soumosir.coursehubbackend.model.helper.ForgetPasswordAppUser;
import com.soumosir.coursehubbackend.repo.AppUserRepo;
import com.soumosir.coursehubbackend.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected LoginAttemptService loginAttemptService;

    @Autowired
    protected ForgotPasswordService forgotPasswordService;

    @Override
    public AppUser saveUser(AppUser appUser) throws Exception {

        appUser.validate();
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        Role defaultrole = roleRepo.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(defaultrole);
        appUser.setRoles(roles);
        log.info("Saving new user {} to database",appUser.getUsername());
        return appUserRepo.save(appUser);
    }

    @Override
    public AppUser updateUser(String username, AppUser appUser) throws Exception {

        appUser.validate();
        AppUser appUserDb = appUserRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with username: " + username));
        log.info("Updating  user {} to database ",appUser.getUsername());

        appUserDb.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserDb.setName(appUser.getName());
        appUserDb.setEmail(appUser.getEmail());
//        Role defaultrole = roleRepo.findByName("ROLE_USER");
//        List<Role> roles = new ArrayList<>();
//        roles.add(defaultrole);
//        appUser.setRoles(roles);
        return appUserDb;
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role to database "+role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Add role to user to database -> "+username+" -> "+roleName);
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        Role role = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("get  user from database "+username );
        return appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));

    }

    @Override
    public AppUser getUserByEmail(String email) {
        log.info("get  user from database "+email);
        return appUserRepo.findByEmail(email).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with email: " + email));

    }

    @Override
    public List<AppUser> getUsers() {
        log.info("get all users from database");
        return appUserRepo.findAll();
    }

    @Override
    public void deleteUser(String username) {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() ->
                new ResourceNotFoundException("user does not exist with username: " + username));

        log.info("Deleting user with username {} from database",username);
        appUserRepo.deleteById(appUser.getId());
    }

    @Override
    public void resetPassword(ForgetPasswordAppUser forgetPasswordAppUser){

        forgetPasswordAppUser.validate();

        String email = forgetPasswordAppUser.getEmail();
        AppUser appUser = appUserRepo.findByEmail(
                email).stream().findFirst().orElseThrow(() ->
                new ResourceNotFoundException("user does not exist with email: " + email));

        if(!forgotPasswordService.verify(email,forgetPasswordAppUser.getCode())){
            log.error("User {} has entered wrong code ",appUser.getUsername());
            throw new ResourceNotFoundException("The code provided is not correct ");
        }

        appUser.setPassword(passwordEncoder.encode(forgetPasswordAppUser.getPassword()));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with id: " + username));

        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            log.info("Ip {} is blocked ",ip);
            throw new RuntimeException("blocked");
        }


        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(),appUser.getPassword(),authorities);

    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}


