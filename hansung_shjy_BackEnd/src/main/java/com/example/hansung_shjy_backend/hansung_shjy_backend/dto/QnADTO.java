package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class QnADTO {

    private Integer qnaID;
    private Date qnaDate;
    private String myAnswer;
    private String otherAnswer;
    private Integer coupleID;
    private Integer qnaNumber;

    public static QnADTO toDTO(QnA entity) {

        Integer coupleid = null;

        if(entity.getCoupleID() != null) coupleid = entity.getCoupleID().getCoupleID();

        try {
            return QnADTO.builder()
                    .qnaID(entity.getQnaID())
                    .qnaDate(entity.getQnaDate())
                    .myAnswer(entity.getMyAnswer())
                    .otherAnswer(entity.getOtherAnswer())
                    .qnaNumber(entity.getQnaNumber())
                    .coupleID(coupleid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
