package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.LoginRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;

import org.springframework.mail.MailException;


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
    public User signup(User user) {
        if (user.getId() == null | user.getPw() == null ||
            user.getName() == null || user.getEmail() == null || user.getBirth() == null ||
                user.getNickname() == null) {
            return null;
        }
        user.setOtherID("otherID");

        System.out.println("signup11:: " + user);

        userRepository.save(user);
        return user;
    }

    // 이메일 인증 ==========================================================================================
    private jakarta.mail.internet.MimeMessage createMessage(String to)throws Exception{
        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ ePw);
        jakarta.mail.internet.MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(jakarta.mail.Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("이메일 인증"); //제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 LoveMore입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(String.valueOf(new InternetAddress("sunho010416@gmail.com","LoveMore")));//보내는 사람

        return message;
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
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    @Override
    public String findNicknameByEmail(String email) throws ExecutionException, InterruptedException {
        String other_nickname = userRepository.findAllByEmail(email);
        if (other_nickname == null) return null;

        else return other_nickname;
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

    // 로그아웃 =============================================================================================
    @Override
    public String logout() throws ExecutionException, InterruptedException {
        return "logout";
    }

    public Integer findUserByUserid(Integer user_id) throws ExecutionException, InterruptedException {
        return userRepository.findUserByUserID(user_id).getUserID();
    }

    @Override
    public Integer findUseridByEmail(String user_email) throws ExecutionException, InterruptedException {
        return userRepository.findUserByEmail(user_email).getUserID();
    }
}
