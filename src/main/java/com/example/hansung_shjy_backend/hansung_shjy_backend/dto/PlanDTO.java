package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class PlanDTO {
    @JsonProperty("planID")
    private Integer planID;

    @JsonProperty("planStartDate")
    private Date planStartDate;

    @JsonProperty("planEndDate")
    private Date planEndDate;

    @JsonProperty("planTitle")
    private String planTitle;

    @JsonProperty("planTraffic")
    private Integer planTraffic;

    @JsonProperty("planHome")
    private String planHome;

    @JsonProperty("userID")
    private Integer userID;

    public static PlanDTO toDTO(Plan entity) {

        Integer userid = null;

        if(entity.getUserID() != null) userid = entity.getUserID().getUserID();

        try {
            return PlanDTO.builder()
                    .planID(entity.getPlanID())
                    .planStartDate(entity.getPlanStartDate())
                    .planEndDate(entity.getPlanEndDate())
                    .planTitle(entity.getPlanTitle())
                    .planTraffic(entity.getPlanTraffic())
                    .planHome(entity.getPlanHome())
                    .userID(userid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
