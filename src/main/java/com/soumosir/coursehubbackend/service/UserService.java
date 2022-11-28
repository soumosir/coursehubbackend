package com.soumosir.coursehubbackend.service;

import com.soumosir.coursehubbackend.model.AppUser;
import com.soumosir.coursehubbackend.model.Role;
import com.soumosir.coursehubbackend.model.helper.ForgetPasswordAppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser appUser) throws Exception;
    AppUser updateUser(String username,AppUser appUser) throws Exception;
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    AppUser getUser(String username);
    AppUser getUserByEmail(String email);
    List<AppUser> getUsers();
    void deleteUser(String username);
    void resetPassword(ForgetPasswordAppUser forgetPasswordAppUser);
}
