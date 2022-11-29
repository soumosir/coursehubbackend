package com.soumosir.coursehubbackend.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.coursehubbackend.cache.ForgotPasswordService;
import com.soumosir.coursehubbackend.model.AppUser;
import com.soumosir.coursehubbackend.model.Role;
import com.soumosir.coursehubbackend.model.helper.AppUserRest;
import com.soumosir.coursehubbackend.model.helper.ForgetPasswordAppUser;
import com.soumosir.coursehubbackend.service.EmailService;
import com.soumosir.coursehubbackend.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final EmailService emailService;

    @Autowired
    protected ForgotPasswordService forgotPasswordService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUserRest>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers().stream().map(AppUserRest::new).collect(Collectors.toList()));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<AppUserRest> getUser(@PathVariable String username) {
        return ResponseEntity.ok().body(new AppUserRest(userService.getUser(username)));
    }

    @PutMapping("/user/{username}")
    public ResponseEntity<AppUserRest> updateUser(@PathVariable String username, @RequestBody AppUser appUser, HttpServletResponse response) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/" + username).toUriString());
        try {
            return ResponseEntity.created(uri).body(new AppUserRest(userService.updateUser(username, appUser)));
        } catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/user/register")
    public ResponseEntity<AppUserRest> saveUser(@RequestBody AppUser appUser, HttpServletResponse response) throws IOException {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/register").toUriString());
        try {
            return ResponseEntity.created(uri).body(new AppUserRest(userService.saveUser(appUser)));
        } catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage() + " ::  Email or Username already exists!");
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username, HttpServletResponse response) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/" + username).toUriString());
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage() + " ::  Username doesn't exists!");
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/resetpassword/{email}")
    public ResponseEntity<String> resetUserPasswordCodeRequest(@PathVariable String email) {

        AppUser appUser = userService.getUserByEmail(email);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/resetpassword/" + email).toUriString());
        String code = forgotPasswordService.setRandomCode(email);
        emailService.sendEmail(appUser.getUsername(), email, code);
        return ResponseEntity.ok().body("Email sent to " + email);
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<String> resetUserPassword(@RequestBody ForgetPasswordAppUser forgetPasswordAppUser, HttpServletResponse response) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/resetpassword").toUriString());
        try {
            userService.resetPassword(forgetPasswordAppUser);
            return ResponseEntity.created(uri).body("Password has been reset!");
        } catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> saveUser(@RequestBody RoleToAppUserForm form) {
        userService.addRoleToUser(form.username, form.roleName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser appUser = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToAppUserForm {
    public String username;
    public String roleName;
}
