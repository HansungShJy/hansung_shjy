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
        String delete = myPageService.userDelete(userid);
        if (delete == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body("delete");
    }

    // 마이페이지 회원 정보 수정 =========================================
    @PatchMapping("/mypage/edit/{userid}")
    public ResponseEntity<Object> modifyUser(@PathVariable Integer userid, @RequestBody UserDTO userDTO) throws ExecutionException, InterruptedException {
        System.out.println("modify Userid:: " + userid);
        User user = myPageService.userModify(userid);  // 유저 찾기

        System.out.println("nickname:: " + userDTO.getNickname() + "birth:: " + userDTO.getBirth());

        if (user == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        try {
            if (userDTO.getNickname() == null) {
                Integer updateBirth = userRepository.updateUserByBirth(userDTO.getBirth(), userDTO.getUserID());
                return ResponseEntity.ok().body("Birth만 수정: " + updateBirth);
            } else if (userDTO.getBirth() == null) {
                Integer updateNickname = userRepository.updateUserByNickname(userDTO.getNickname(), userDTO.getUserID());
                return ResponseEntity.ok().body("Nickname만 수정: " + updateNickname);
            } else {
                Integer updateBirthAndNickname = userRepository.updateUserByNicknameAndBirth(userDTO.getNickname(), userDTO.getBirth(), userDTO.getUserID());
                return ResponseEntity.ok().body("둘 다 수정: " + updateBirthAndNickname);
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during user update");
        }
    }
}
