package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
@Getter
@Setter
@Builder

public class User {

    @Id
    @GeneratedValue
    @Column(name = "userID")
    private Integer userID;

    @Column(name = "id")
    private String id;

    @Column(name = "pw")
    private String pw;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birth")
    private Date birth;  //0000-00-00 형식

    @Column(name = "otherID")
    private String otherID;

    @Column(name = "Dday")
    private Integer Dday;

    public static User toEntity(UserDTO dto) {
        return User.builder()
                .userID(dto.getUserID())
                .id(dto.getId())
                .pw(dto.getPw())
                .name(dto.getName())
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .birth(dto.getBirth())
                .otherID(dto.getOtherID())
                .Dday(dto.getDday())
                .build();
    }
}
