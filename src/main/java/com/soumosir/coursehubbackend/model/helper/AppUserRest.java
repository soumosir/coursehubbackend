package com.soumosir.coursehubbackend.model.helper;

import com.soumosir.coursehubbackend.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRest {

    private Long id;
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String username;
    @NotEmpty
    @NotNull
    @Email(message = "Email should be valid")
    private String email;
    private Collection<String> roles = new ArrayList<>();

    public AppUserRest(AppUser appUser){
        this.id = appUser.getId();
        this.name = appUser.getName();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.roles = appUser.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
    }

}


