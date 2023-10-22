package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    UserRepository userRepository;


    // 회원가입 ====================================================================================
    // 아이디 중복확인
    @Override
    public String verifyID(String id) {
        if (id==null) return "null exception";
        User user = userRepository.findUserById(id);
        if (user == null) return "false";
        else return "true";
    }

    // 유저 등록
    @Override
    public UserDTO signup(UserDTO user) {
        if (user.getUserID() == null || user.getId() == null | user.getPw() == null ||
        user.getName() == null || user.getEmail() == null || user.getBirth() == null || user.getNickname() == null) {
            return null;
        }

        user.setOtherID("who");
        user.setDday(0);

        userRepository.save(User.toEntity(user));
        return user;
    }

    // 커플 연결


    // 로그인 ======================================================================================
}
