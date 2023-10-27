package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.LoginRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 ====================================================================================================
    // 아이디 중복확인
    @PostMapping("/verify/id")
    public ResponseEntity<Object> verifyID(@RequestBody String id) throws ExecutionException, InterruptedException {
        System.out.println("verifyID:: " + id);
        String verifyResult = userService.verifyID(id);
        System.out.println("verifyResult:: " + verifyResult);
        if(verifyResult.equals("null exception")) return ResponseEntity.badRequest().body("null exception");
        else return ResponseEntity.ok().body(verifyResult);
    }

    // 회원 등록
    @PostMapping("/signup")
    public ResponseEntity<Object> signupRequest(@RequestBody UserDTO user) throws ExecutionException, InterruptedException {
        System.out.println("signup:: " + user);
        UserDTO userSignup =userService.signup(user);
        if (userSignup == null) return new ResponseEntity<Object>("null exception", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // 커플 연결
    @PostMapping("/connect")
    public String emailConnect(@RequestParam String email) throws Exception {

        String code = userService.sendAuthenticationMessage(email);
        System.out.println("connect:: " + code);

        return code;
    }

    // 로그인 ======================================================================================================
//    @PostMapping("/login")
//    public ResponseEntity<Object> loginRequest(@RequestBody LoginRequest loginRequest) throws ExecutionException, InterruptedException {
//
//    }
}
