package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
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

    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: QNA, One: User
    @JoinColumn(name = "userID", referencedColumnName = "userID", foreignKey = @ForeignKey(name = "userID_Diary"))
    private User userid;

    public static Diary toEntity(DiaryDTO dto) {
        return Diary.builder()
                .diaryID(dto.getDiaryID())
                .diaryDate(dto.getDiaryDate())
                .myDiary(dto.getMyDiary())
                .otherDiary(dto.getOtherDiary())
                .build();
    }
}
