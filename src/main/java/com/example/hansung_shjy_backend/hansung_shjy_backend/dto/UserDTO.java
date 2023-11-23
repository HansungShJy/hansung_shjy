package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

//Data Transfer Object
//계층간의 데이터 교환을 위한 객체
//DB에서 데이터를 받아와서 Service나 Controller 등으로 보낼 때 사용하는 객체
public class UserDTO {

    private Integer userID;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String nickname;
    private Date birth;
    private String otherID;
    private String Dday;

    public UserDTO(Integer userid) {
        this.userID = userid;
    }

    public static UserDTO toDTO(User entity) {
        try {
            return UserDTO.builder()
                    .userID(entity.getUserID())
                    .id(entity.getId())
                    .pw(entity.getPw())
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .nickname(entity.getNickname())
                    .birth(entity.getBirth())
                    .otherID(entity.getOtherID())
                    .Dday(entity.getDday())
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
