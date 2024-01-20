package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;


import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ImageDTO {

    // 이미지 관련
    private Integer imageID;
    private String imageName;    // 업로드한 이미지의 원래 파일명
    private String imageOriName; // 실제 저장되는 파일 (이미지 중복 방지 위해)
    private String imageUrl;     // 이미지 경로
    private Integer diaryID;     // 외래키

    public static ImageDTO toDTO(Image entity) {
        Integer diaryid = null;

        if (entity.getImageID() != null) diaryid = entity.getDiary().getDiaryID();

        try {
            return ImageDTO.builder()
                    .imageID(entity.getImageID())
                    .imageName(entity.getImageName())
                    .imageOriName(entity.getImageOriName())
                    .imageUrl(entity.getImageUrl())
                    .diaryID(diaryid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
