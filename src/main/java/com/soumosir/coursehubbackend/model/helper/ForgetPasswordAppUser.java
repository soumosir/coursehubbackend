package com.soumosir.coursehubbackend.model.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@Data
@NoArgsConstructor @AllArgsConstructor
public class ForgetPasswordAppUser {

    @Email(message = "Email should be valid")
    @NotEmpty
    @NotNull
    private String email;
    @NotEmpty
    @NotNull
    private String password;
    @NotEmpty
    @NotNull
    private String code;

    public void validate() throws ValidationException {

        if (password.length() < 12 || password.length() > 30) {
            log.error("Password length should be greater than 8 and less than 30 :" + password);
            throw new ValidationException("Password length should be greater than 8 and less than 30 :" + password);
        }
        if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            log.error("Password should contain one lower case, upper case, one numeral and one special character ");
            throw new ValidationException("Password should contain one lower case, upper case, one numeral and one special character ");
        }


        if(email==null ||(email!=null && (email.length()<3 || email.length()>30))){
            log.error("Email is not valid should be more than 3 and les than 30 : "+ email);
            throw new ValidationException("Email is not valid should be more than 3 and les than 30 : "+email);
        }


        if(!email.matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")){
            log.error("Email is not valid "+ email );
            throw new ValidationException("Email is not valid "+ email);
        }

        if(code==null){
            log.error("Code cannot be null ");
            throw new ValidationException("Code is not valid "+ code);
        }


    }

}
