package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class UsersId implements Serializable {
    private Integer userID;
    private String otherID;
}
