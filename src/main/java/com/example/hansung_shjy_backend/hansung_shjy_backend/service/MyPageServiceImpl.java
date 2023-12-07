package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 마이페이지 회원 탈퇴 ==========================================================
    @Override
    @Transactional
    public String userDelete(Integer userid) throws ExecutionException, InterruptedException {
        User deleteUser = userRepository.findByUserID(userid);
        if (deleteUser == null) return null;
        else return "delete";
    }

    // 마이페이지 회원 정보 수정 =======================================================
    @Override
    public User userModify(Integer userid) throws ExecutionException, InterruptedException {
        user = userRepository.findUserByUserID(userid);
//        User updateUser = userRepository.(user);
        if (user == null) return null;
        else return user;
    }

}
