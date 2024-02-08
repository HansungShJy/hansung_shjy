package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DiaryEditDTO {
    @JsonProperty("diaryDate") private String diaryDate;
    @JsonProperty("myDiary") private String myDiary;
    @JsonProperty("otherDiary") private String otherDiary;
    @JsonProperty("userID") private Integer userID;
    @JsonProperty(value = "file", required = false) private MultipartFile file;
}
