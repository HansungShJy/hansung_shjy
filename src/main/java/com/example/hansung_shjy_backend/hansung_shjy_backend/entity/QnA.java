package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qna")
@Getter
@Setter
@Builder
@Entity

public class QnA implements Serializable {

    @Id
    @GeneratedValue     //auto_increment
    @Column(name = "qnaID")
    private Integer qnaID;

    @Column(name = "qnaDate")
    private Date qnaDate;

    @Column(name = "myAnswer")
    private String myAnswer;

    @Column(name = "otherAnswer", nullable = true)
    private String otherAnswer;


    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: QNA, One: User(myID) (Integer)
    @JsonManagedReference
    @JoinColumn(name = "userID", referencedColumnName = "userID", foreignKey = @ForeignKey(name = "userID"))
    private User userID;

    @Column(name = "otherID")
    private String otherID;


    public static QnA toEntity(QnADTO dto) {
        return QnA.builder()
                .qnaID(dto.getQnaID())
                .qnaDate(dto.getQnaDate())
                .myAnswer(dto.getMyAnswer())
                .otherAnswer(dto.getOtherAnswer())
                .build();
    }
}
