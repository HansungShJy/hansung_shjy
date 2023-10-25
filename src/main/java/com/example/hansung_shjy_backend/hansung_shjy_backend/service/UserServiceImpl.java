package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    JavaMailSender emailSender;

    public static final String ePw = createKey();

    // 회원가입 ====================================================================================
    // 아이디 중복확인
    @Override
    public String verifyID(String id) {
        if (id == null) return "null exception";

        User user = userRepository.findUserById(id);

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

    // 커플 연결
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
    // FE: 로그인할 때 user 넘겨주면 거기서 userID랑 otherID 받아와서 쿠키에 저장
    @Override
    public UserDTO login(String id, String pw) throws ExecutionException, InterruptedException {
        User userEntity = userRepository.findUserById(id);
        if (userEntity == null) return null;        //id에 맞는 entity가 없을 때

        UserDTO user = UserDTO.toDTO(userEntity);
        if (user == null) return null;              //userEntity에 맞는 user가 없을 때

        if (user.getPw().equals(pw)) return user;   //user db에 pw와 받아온 pw가 일치하면 user 리턴
        else return null;
    }
}
