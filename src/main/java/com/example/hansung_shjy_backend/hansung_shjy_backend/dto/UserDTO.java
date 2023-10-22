package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private Integer userID;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String nickname;
    private Date birth;
    private String otherID;
    private int Dday;

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
