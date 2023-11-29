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
    private String qnaTitle;
    private Date qnaDate;
    private String myAnswer;
    private String otherAnswer;
    private Integer userID;

    public static QnADTO toDTO(QnA entity) {

        Integer userid = null;

        if(entity.getUserID() != null) userid = entity.getUserID().getUserID();

        try {
            return QnADTO.builder()
                    .qnaID(entity.getQnaID())
                    .qnaTitle(entity.getQnaTitle())
                    .qnaDate(entity.getQnaDate())
                    .myAnswer(entity.getMyAnswer())
                    .otherAnswer(entity.getOtherAnswer())
                    .userID(userid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
