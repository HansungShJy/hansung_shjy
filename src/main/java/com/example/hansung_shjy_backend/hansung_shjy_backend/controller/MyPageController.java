package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.LoginRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.MyPageService;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
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

    // 마이페이지 화면 탈퇴 ==============================================
    @DeleteMapping("/mypage/accountdelete")
    public ResponseEntity<Object> deleteUser(@RequestBody Integer userid) throws ExecutionException, InterruptedException {
        System.out.println("delete userid:: " + userid);
        String delete = myPageService.userDelete(userid);
        if (delete == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body("delete");
    }
}
