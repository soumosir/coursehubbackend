package com.soumosir.coursehubbackend.service;


public interface EmailService {
    void sendEmail(String username,String email,String code);
}
