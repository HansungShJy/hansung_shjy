package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diary")
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Entity

// diary 테이블
public class Diary implements Serializable {
    @Id
    @GeneratedValue     //auto_increment
    @Column(name = "diaryID")
    private Integer diaryID;

    @Column(name = "diaryDate")
    private Date diaryDate;

    @Column(name = "myDiary")
    private String myDiary;

    @Column(name = "otherDiary")
    private String otherDiary;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "fileOriName")
    private String fileOriName;

    @Column(name = "fileurl")
    private String fileUrl;

    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: User, One: Couple
    @JsonManagedReference
    @JoinColumn(name = "coupleID", referencedColumnName = "coupleID", foreignKey = @ForeignKey(name = "coupleID_diary"))
    private Couple couple;

    public static Diary toEntity(DiaryDTO dto) {
        return Diary.builder()
                .diaryID(dto.getDiaryID())
                .diaryDate(dto.getDiaryDate())
                .myDiary(dto.getMyDiary())
                .otherDiary(dto.getOtherDiary())
                .fileName(dto.getFileName())
                .fileOriName(dto.getFileOriName())
                .fileUrl(dto.getFileUrl())
                .build();
    }
}
