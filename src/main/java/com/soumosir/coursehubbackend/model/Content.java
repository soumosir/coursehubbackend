package com.soumosir.coursehubbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Slf4j
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotEmpty
    @NotNull
    private String name;
//    @Column(unique=true)
    @NotEmpty
    @NotNull
    private String type;


    @NotEmpty
    @NotNull
    private String url;

    @NotNull
    @NotEmpty
    private String username;


    private String description;


    public void validate() throws ValidationException {



        if(name==null || (name!=null && (name.length()<3|| name.length()>200))){
            log.error("name is less than 3 letters or more than 20 : "+ name );
            throw new ValidationException("name is less than 3 letters or more than 20 : "+ name );
        }


        if(!type.matches("^[a-zA-Z0-9]+$")){
            log.error("code is should be alphanumeric A-Z , a-z or 0-9 : "+ type );
            throw new ValidationException("Username is should be alphanumeric A-Z , a-z or 0-9 : "+type);
        }

        if(type==null ||(type!=null && (type.length()<2 || type.length()>3000))){
            log.error("type is not valid should be more than 2 and les than 30 : "+ type );
            throw new ValidationException("type is not valid should be more than 3 and les than 30 : "+type);
        }



    }
}
