package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DiaryRequest {  // 홈화면 & 전체보기 리스트에 시용
    @JsonProperty("diary")
    private Diary diary;

    @JsonProperty("Image")
    private Image image;

}
