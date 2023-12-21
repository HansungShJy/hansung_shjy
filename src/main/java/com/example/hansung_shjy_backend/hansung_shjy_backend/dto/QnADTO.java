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
    private Integer userID;
    private String otherID;

    public static QnADTO toDTO(QnA entity) {

        Integer userid = null;
        String otherid = null;

        if(entity.getUserID() != null) userid = entity.getUserID().getUserID();
        if(entity.getOtherID() != null) otherid = entity.getOtherID();

        try {
            return QnADTO.builder()
                    .qnaID(entity.getQnaID())
                    .qnaDate(entity.getQnaDate())
                    .myAnswer(entity.getMyAnswer())
                    .otherAnswer(entity.getOtherAnswer())
                    .userID(userid)
                    .otherID(otherid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
