package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.ImageDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Entity

public class Image implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "imageID")
    private Integer imageID;

    @Column(name = "imageName")
    private String imageName;

    @Column(name = "imageOriName")
    private String imageOriName;

    @Column(name = "imageurl")
    private String imageUrl;

    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: Image, One: Diary= "diaryID", referencedColumnName = "diaryID", foreignKey = @ForeignKey(name = "diaryID_image"))
    private Diary diary;


    public static Image toEntity(ImageDTO dto) {
        return Image.builder()
                .imageID(dto.getImageID())
                .imageName(dto.getImageName())
                .imageOriName(dto.getImageOriName())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}
