package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.PlanDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class PlanDetailDTO {

    @JsonProperty("planDetailID")
    private Integer planDetailID;

    @JsonProperty("planDayNumber")
    private Integer planDayNumber;

    @JsonProperty("planNumber")
    private Integer planNumber;

    @JsonProperty("planLocation")
    private String planLocation;

    @JsonProperty("planPrice")
    private Integer planPrice;

    @JsonProperty("planStartTime")
    private String planStartTime;

    @JsonProperty("planEndTime")
    private String planEndTime;

    @JsonProperty("planCheck")
    private Boolean planCheck;

    @JsonProperty("planID")
    private Integer planID;

    public static PlanDetailDTO toDTO(PlanDetail entity) {

        Integer planid = null;

        if(entity.getPlanID() != null) planid = entity.getPlanID().getPlanID();

        try {
            return PlanDetailDTO.builder()
                    .planDetailID(entity.getPlanDetailID())
                    .planDayNumber(entity.getPlanDayNumber())
                    .planNumber(entity.getPlanNumber())
                    .planLocation(entity.getPlanLocation())
                    .planPrice(entity.getPlanPrice())
                    .planStartTime(entity.getPlanStartTime())
                    .planEndTime(entity.getPlanEndTime())
                    .planCheck(entity.getPlanCheck())
                    .planID(planid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
