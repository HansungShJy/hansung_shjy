package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;

import java.util.concurrent.ExecutionException;

public interface UserService {


    // 회원가입 ===============================================================
    // 아이디 중복체크
    String verifyID(String id) throws ExecutionException, InterruptedException;

    // 유저 등록
    UserDTO signup(UserDTO user) throws ExecutionException, InterruptedException;

    // 커플 연결

}
