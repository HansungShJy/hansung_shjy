package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class DiaryDTO {

    private Integer diaryID;
    private Date diaryDate;
    private String myDiary;
    private String otherDiary;
    private Integer userID;

    public static DiaryDTO toDTO(Diary entity) {

        Integer userid = null;

        if(entity.getUserid() != null) userid = entity.getUserid().getUserID();

        try {
            return DiaryDTO.builder()
                    .diaryID(entity.getDiaryID())
                    .diaryDate(entity.getDiaryDate())
                    .myDiary(entity.getMyDiary())
                    .otherDiary(entity.getOtherDiary())
                    .userID(userid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
