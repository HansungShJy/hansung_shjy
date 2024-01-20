package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter

public class DiarySaveRequest {
    @JsonProperty("diaryDTO")
    private DiaryDTO diaryDTO;

    @JsonProperty("imageFile")
    private MultipartFile imageDTO;
}
