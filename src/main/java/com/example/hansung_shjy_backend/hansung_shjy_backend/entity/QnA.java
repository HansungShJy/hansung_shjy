package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qna")
@Getter
@Setter
@Builder
@Entity

public class QnA {

    @Id
    @GeneratedValue     //auto_increment
    @Column(name = "qnaID")
    private Integer qnaID;

    @Column(name = "qnaTitle")
    private String qnaTitle;

    @Column(name = "qnaDate")
    private Date qnaDate;

    @Column(name = "myAnswer")
    private String myAnswer;

    @Column(name = "otherAnswer")
    private String otherAnswer;


    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: QNA, One: User
    @JoinColumn(name = "userID", referencedColumnName = "userID", foreignKey = @ForeignKey(name = "userID"))
    private User userid;


    public static QnA toEntity(QnADTO dto) {
        return QnA.builder()
                .qnaID(dto.getQnaID())
                .qnaTitle(dto.getQnaTitle())
                .qnaDate(dto.getQnaDate())
                .myAnswer(dto.getMyAnswer())
                .otherAnswer(dto.getOtherAnswer())
                .build();
    }
}
