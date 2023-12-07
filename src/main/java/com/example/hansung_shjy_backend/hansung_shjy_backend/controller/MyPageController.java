package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController

public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @Autowired
    private UserRepository userRepository;


    // 마이페이지 첫 화면 ================================================
    @GetMapping("/mypage")
    public ResponseEntity<Object> myPageFirst(@RequestParam Integer userid) throws ExecutionException, InterruptedException {
        System.out.println("mypage userid:: " + userid);
        User userDTO = myPageService.user(userid);
        if (userDTO == null) return new ResponseEntity<Object>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(userDTO);
    }

    // 마이페이지 회원 탈퇴 ==============================================
    @DeleteMapping("/mypage/accountdelete/{userid}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer userid) throws ExecutionException, InterruptedException {
        System.out.println("delete userid:: " + userid);
        User userDTO = userRepository.findUserByUserID(userid);
//        String delete = myPageService.userDelete(userid);
        userRepository.delete(userDTO);
        if (userid == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body("delete");
    }

    // 마이페이지 회원 정보 수정 =========================================
    @PatchMapping("/mypage/edit/{userid}")
    public ResponseEntity<Object> modifyUser(@PathVariable Integer userid, @RequestBody UserDTO userDTO) throws ExecutionException, InterruptedException {
        System.out.println("modify Userid:: " + userid);
        User user = myPageService.userModify(userid);  // 유저 찾기

        System.out.println("nickname:: " + userDTO.getNickname() + "birth:: " + userDTO.getBirth());

        if (user == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);

        user.setNickname(userDTO.getNickname());
        user.setBirth(userDTO.getBirth());

        userRepository.save(user);

        return ResponseEntity.ok().body(user);

    }
}
