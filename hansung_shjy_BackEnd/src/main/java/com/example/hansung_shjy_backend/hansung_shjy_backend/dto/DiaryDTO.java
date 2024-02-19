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

    // 일기 관련
    private Integer diaryID;
    private Date diaryDate;
    private String myDiary;
    private String otherDiary;
    private String imageName;    // 업로드한 이미지의 원래 파일명
    private String imageOriName; // 실제 저장되는 파일 (이미지 중복 방지 위해)
    private String imageUrl;     // 이미지 경로
    private Integer coupleID;

    public static DiaryDTO toDTO(Diary entity) {

        Integer coupleid = null;

        if(entity.getCouple() != null) coupleid = entity.getCouple().getCoupleID();

        try {
            return DiaryDTO.builder()
                    .diaryID(entity.getDiaryID())
                    .diaryDate(entity.getDiaryDate())
                    .myDiary(entity.getMyDiary())
                    .otherDiary(entity.getOtherDiary())
                    .imageName(entity.getImageName())
                    .imageOriName(entity.getImageOriName())
                    .imageUrl(entity.getImageUrl())
                    .coupleID(coupleid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
