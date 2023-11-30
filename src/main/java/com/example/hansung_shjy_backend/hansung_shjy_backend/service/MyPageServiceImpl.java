package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    @Autowired
    private UserRepository userRepository;

    User user;

    // 마이페이지 첫 화면 ===========================================================
    @Override
    public User user(Integer userid) throws ExecutionException, InterruptedException {
        if (userid == null) return null;

        user = userRepository.findUserByUserID(userid);
        if (user == null) return null;
        else return user;
    }

    // 마이페이지 화면 탈퇴 ==========================================================
    @Override
    public String userDelete(Integer userid) throws ExecutionException, InterruptedException {
        User user = userRepository.findUserByUserID(userid);
        if (user == null) return null;
        else return "delete";
    }



}
