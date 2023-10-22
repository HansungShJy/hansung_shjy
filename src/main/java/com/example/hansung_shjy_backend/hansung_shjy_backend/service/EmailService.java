package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

public interface EmailService {
    String sendAuthenticationMessage(String to) throws Exception;
}
