package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface UserService {


    // 회원가입 ===============================================================
    // 아이디 중복체크
    String verifyID(String id) throws ExecutionException, InterruptedException;

    // 유저 등록
    UserDTO signup(UserDTO user) throws ExecutionException, InterruptedException;

    // 커플 연결
    String sendAuthenticationMessage(String to) throws Exception;

    // 로그인
    Map<Long, String> login(String id, String pw, HttpServletResponse response) throws ExecutionException, InterruptedException;
}
