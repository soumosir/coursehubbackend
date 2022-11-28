package com.soumosir.coursehubbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotEmpty
    @NotNull
    private String name;
    @Column(unique=true)
    @NotEmpty
    @NotNull
    private String username;
    @NotEmpty
    @NotNull
    @Column(unique=true)
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty
    @NotNull
    private String password;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public void validate() throws ValidationException {

        if (password.length() < 12 || password.length() > 30) {
            log.error("Password length should be greater than 8 and less than 30 :" + password);
            throw new ValidationException("Password length should be greater than 8 and less than 30 :" + password);
        }
        if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            log.error("Password should contain one lower case, upper case, one numeral and one special character ");
            throw new ValidationException("Password should contain one lower case, upper case, one numeral and one special character ");
        }

        if(username==null || (username!=null && (username.length()<3|| username.length()>20))){
            log.error("Username is less than 3 letters or more than 20 : "+ username );
            throw new ValidationException("Username is less than 3 letters or more than 20 : "+ username );
        }


        if(!username.matches("^[a-zA-Z0-9]+$")){
            log.error("Username is should be alphanumeric A-Z , a-z or 0-9 : "+ username );
            throw new ValidationException("Username is should be alphanumeric A-Z , a-z or 0-9 : "+username);
        }

        if(email==null ||(email!=null && (email.length()<3 || email.length()>30))){
            log.error("Email is not valid should be more than 3 and les than 30 : "+ email );
            throw new ValidationException("Email is not valid should be more than 3 and les than 30 : "+email);
        }


        if(!email.matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")){
            log.error("Email is not valid "+ email );
            throw new ValidationException("Email is not valid "+ email);
        }

        if(name==null || (name!=null && (name.length()<3 || name.length()>30))){
            throw new ValidationException("Name is less than 3 letters or more than 30 "+name);
        }


    }
}
