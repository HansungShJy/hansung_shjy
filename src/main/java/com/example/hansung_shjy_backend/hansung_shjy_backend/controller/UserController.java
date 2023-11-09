package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.IdFindRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.LoginRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PwFindRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private UserDTO userDTO;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 ====================================================================================================
    // 아이디 중복확인
    @PostMapping("/verify/id")
    public ResponseEntity<Object> verifyID(@RequestBody HashMap<String, Object> id) throws ExecutionException, InterruptedException {
        System.out.println("verifyID:: " + id);

        String userid = (String) id.get("id");
        System.out.println("verfityidid:: " + userid);

        String verifyResult = userService.verifyID(userid);
        System.out.println("verifyResult:: " + verifyResult);
        if(verifyResult.equals("null exception")) return ResponseEntity.badRequest().body("null exception");
        else return ResponseEntity.ok().body(verifyResult);
    }

    // 이메일 인증
    @PostMapping("/confirm/email")
    public String emailCode(@RequestParam(value = "email", required = false) String email) throws Exception {
        System.out.println("confirm Email:: " + email);
        String code = userService.sendAuthenticationMessage(email);
        System.out.println("connect:: " + code);

        return code;
    }

    // 회원 등록
    @PostMapping("/signup")
    public ResponseEntity<Object> signupRequest(@RequestBody UserDTO user) throws ExecutionException, InterruptedException {
        System.out.println("signup:: " + user);

        User userSignup =userService.signup(User.toEntity(user));
        System.out.println("signupup:: " + userSignup);

        if (userSignup == null) return new ResponseEntity<Object>("null exception", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // 커플 이메일 인증
    @PostMapping("/confirm/email/couple")
    public String coupleEmailCode(@RequestParam String email) throws Exception {

        String code = userService.sendAuthenticationMessage(email);
        System.out.println("connect:: " + code);

        return code;
    }

    // 커플 연결
    @PostMapping("/connect")
    public ResponseEntity<Object> connectCouple(@RequestBody HashMap<String, Object> coupleInfo) throws Exception {
        System.out.println("connectCouple:: " + coupleInfo);

        String userid = (String) coupleInfo.get("id");      // 내 아이디
        String email = (String) coupleInfo.get("otherid");  // 상대방 아이디
        String dday = (String) coupleInfo.get("dday");      // 디데이
        System.out.println("id:: " + ", " + userid + "email:: " + email + ", " + dday);


        String other_nickname = userService.findNicknameByEmail(email);
        System.out.println("connect nickname:: " + other_nickname);
        // 상대방도 otherID, Dday 서로의 값으로 들어가야함


        if (other_nickname == null) return new ResponseEntity<Object>("null exception", HttpStatus.BAD_REQUEST);

        else {
            User user = userService.findUserByUserid(userid);
            User otheruser = userService.findUserByNickname(other_nickname);
            System.out.println("user: " + user);
            System.out.println("otherUser: " + otheruser);

            user.setOtherID(other_nickname);
            user.setDday(dday);

            otheruser.setOtherID(user.getNickname());
            otheruser.setDday(dday);

            userRepository.save(user);
            return ResponseEntity.ok().body(user);
        }
    }

    // 로그인 ======================================================================================================
    @PostMapping("/login")
    public ResponseEntity<Object> loginRequest(@RequestBody LoginRequest loginRequest) throws ExecutionException, InterruptedException {
        System.out.println("login:: " + loginRequest);
        UserDTO userLogin = userService.login(loginRequest);
        if (userLogin == null) return new ResponseEntity<Object>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(userLogin);
    }

    // 비밀번호 찾기 ==============================================================================================
    @PostMapping("/findpw")
    public ResponseEntity<Object> findPwRequest(@RequestBody PwFindRequest pwFindRequest) throws ExecutionException, InterruptedException {
        System.out.println("findpw:: " + pwFindRequest);
        String userPw = userService.findPwById(pwFindRequest.getId());
        if (userPw == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(userPw);
    }

    // 아이디 찾기 ==============================================================================================
    @PostMapping("/findid")
    public ResponseEntity<Object> findIdRequest(@RequestBody IdFindRequest idFindRequest) throws ExecutionException, InterruptedException {
        System.out.println("findid:: " + idFindRequest);
        String userId = userService.findIdByEmail(idFindRequest.getEmail());
        if (userId == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(userId);
    }

    // 로그아웃 =================================================================================================
    // @GetMapping("/logout") -> 405 error
    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(userService.logout());
    }
}
