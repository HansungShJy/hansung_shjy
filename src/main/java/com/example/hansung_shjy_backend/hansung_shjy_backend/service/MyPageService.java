package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;

import java.util.concurrent.ExecutionException;

public interface MyPageService {

    // 마이페이지 첫 화면 ===========================================================
    User user(Integer userid) throws ExecutionException, InterruptedException;


}
