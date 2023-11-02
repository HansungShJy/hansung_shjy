package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.LoginRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;

import java.util.concurrent.ExecutionException;

public interface UserService {


    // 회원가입 ===============================================================
    // 아이디 중복체크
    String verifyID(String id) throws ExecutionException, InterruptedException;

    // 유저 등록
    User signup(User user) throws ExecutionException, InterruptedException;

    // 커플 연결
    String sendAuthenticationMessage(String to) throws Exception;

    // 로그인 ==================================================================
    UserDTO login(LoginRequest loginRequest) throws ExecutionException, InterruptedException;

    // 비밀번호 찾기 ============================================================
    String findPwById(String userId) throws ExecutionException, InterruptedException;

    // 아이디 찾기 ==============================================================
    String findIdByEmail(String userEmail) throws ExecutionException, InterruptedException;

    // 로그아웃 =================================================================
    String logout() throws ExecutionException, InterruptedException;

    Integer findUserByUserid(Integer user_id) throws ExecutionException, InterruptedException;

    Integer findUseridByEmail(String user_email) throws ExecutionException, InterruptedException;
}
