package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.IdFindRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.LoginRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PwFindRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    JavaMailSender emailSender;

    User user;

    public static final String ePw = createKey();

    // 회원가입 ====================================================================================
    // 아이디 중복확인
    @Override
    public String verifyID(String id) {
        if (id == null) return "null exception";

        if (userRepository != null) {
            user = userRepository.findUserById(id);
        } else {
            return "false";
        }

        if (user == null) return "false";
        else return "true";
    }

    // 유저 등록
    @Override
    public UserDTO signup(UserDTO user) {
        if (user.getUserID() == null || user.getId() == null | user.getPw() == null ||
            user.getName() == null || user.getEmail() == null || user.getBirth() == null ||
                user.getNickname() == null) {
            return null;
        }

        userRepository.save(User.toEntity(user));
        return user;
    }

    // 커플 연결 -> 코드 생성 & 부여
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0 -> key.append((char) ((int) (rnd.nextInt(26)) + 97));

                //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                case 1 -> key.append((char) ((int) (rnd.nextInt(26)) + 65));

                //  A~Z
                case 2 -> key.append((rnd.nextInt(10)));

                // 0~9
            }
        }
        return key.toString();
    }
    @Override
    public String sendAuthenticationMessage(String to) throws Exception {
        System.out.println("message to:: " + to);
//        MimeMessage message = createMessage(to);
//        try{//예외처리
//            emailSender.send(message);
//        }catch(MailException es){
//            es.printStackTrace();
//            throw new IllegalArgumentException();
//        }
        return ePw;
    }

    // 로그인 ======================================================================================
    // FE: 로그인할 때 user 넘겨주면 거기서 userID 쿠키에 저장
    @Override
    public UserDTO login(LoginRequest loginRequest) throws ExecutionException, InterruptedException {
        User userEntity = userRepository.findUserById(loginRequest.getId());
        if (userEntity == null) return null;        //id에 맞는 entity가 없을 때

        UserDTO user = UserDTO.toDTO(userEntity);
        if (user == null) return null;              //userEntity에 맞는 user가 없을 때

        if (user.getPw().equals(loginRequest.getPw())) { //user db에 pw와 받아온 pw가 일치하면 user 리턴
            return user;
        }
        else return null;
    }

    // 비밀번호 찾기 ======================================================================================
    @Override
    public String findPwById(String userId) throws ExecutionException, InterruptedException {
        User userEntity = userRepository.findUserById(userId);
        if (userEntity == null) return null;

        UserDTO user = UserDTO.toDTO(userEntity);
        if (user == null) return null;

        if (user.getId().equals(userId)) {
            return user.getPw();
        } else {
            return null;
        }
    }

    // 아이디 찾기 =========================================================================================
    @Override
    public String findIdByEmail(String userEmail) throws ExecutionException, InterruptedException {
        User userEntity = userRepository.findUserByEmail(userEmail);
        if (userEntity == null) return null;

        UserDTO user = UserDTO.toDTO(userEntity);
        if (user == null) return null;

        if (user.getEmail().equals(userEmail)) {
            return user.getId();
        } else {
            return null;
        }
    }
}
