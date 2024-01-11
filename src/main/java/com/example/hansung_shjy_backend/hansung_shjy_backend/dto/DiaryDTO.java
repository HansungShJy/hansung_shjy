package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
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
    private Integer coupleID;

    private String fileName;
    private String fileOriName;
    private String fileUrl;

    public static DiaryDTO toDTO(Diary entity) {

        Integer coupleid = null;

        if(entity.getCouple() != null) coupleid = entity.getCouple().getCoupleID();

        try {
            return DiaryDTO.builder()
                    .diaryID(entity.getDiaryID())
                    .diaryDate(entity.getDiaryDate())
                    .myDiary(entity.getMyDiary())
                    .otherDiary(entity.getOtherDiary())
                    .fileName(entity.getFileName())
                    .fileOriName(entity.getFileOriName())
                    .fileUrl(entity.getFileUrl())
                    .coupleID(coupleid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
