package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    UserRepository userRepository;


    // 회원가입 ====================================================================================
    // 회원가입 -> 아이디 중복확인
    @Override
    public String verifyID(String id) {
//        return null;
        if (id==null) return "null exception";
        User user = userRepository.findUserById(id);
        if (user == null) return "false";
        else return "true";
    }




    // 로그인 ======================================================================================
}
