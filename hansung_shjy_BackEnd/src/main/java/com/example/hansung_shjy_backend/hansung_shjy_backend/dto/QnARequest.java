package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class QnARequest {
    @JsonProperty("qnaID")
    private Integer qnaID;

    @JsonProperty("qnaDate")
    private Date qnaDate;

    @JsonProperty("myAnswer")
    private String myAnswer;

    @JsonProperty("otherAnswer")
    private String otherAnswer;

    @JsonProperty("coupleID")
    private Integer coupleID;

    @JsonProperty("qnaNumber")
    private Integer qnaNumber;

    @JsonProperty("userID")
    private Integer userID;

    public QnARequest(Date qnaDate, String myAnswer, String otherAnswer, Integer coupleID, Integer qnaNumber, Integer userID) {
        this.qnaDate = qnaDate;
        this.myAnswer = myAnswer;
        this.otherAnswer = otherAnswer;
        this.coupleID = coupleID;
        this.qnaNumber = qnaNumber;
        this.userID = userID;
    }
}
