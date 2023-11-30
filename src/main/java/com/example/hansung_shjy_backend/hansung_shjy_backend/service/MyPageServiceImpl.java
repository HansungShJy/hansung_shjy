package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

public class MyPageServiceImpl implements MyPageService {

    @Autowired
    private UserRepository userRepository;

    User user;

    @Override
    public User user(Integer userid) throws ExecutionException, InterruptedException {
        System.out.println("mypage:: " + userid);
        if (userid == null) return null;

        user = userRepository.findUserByUserID(userid);
        if (user == null) return null;
        else return user;
    }
}
