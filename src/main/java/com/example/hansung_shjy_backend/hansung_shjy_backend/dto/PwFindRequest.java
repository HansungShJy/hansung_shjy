package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PwFindRequest {
    String id;
    String email;
    Date birth;
}
