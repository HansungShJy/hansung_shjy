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

    @JsonProperty("coupleID")
    private Integer coupleID;

    public static PlanDTO toDTO(Plan entity) {

        Integer coupleid = null;

        if(entity.getCouple() != null) coupleid = entity.getCouple().getCoupleID();

        try {
            return PlanDTO.builder()
                    .planID(entity.getPlanID())
                    .planStartDate(entity.getPlanStartDate())
                    .planEndDate(entity.getPlanEndDate())
                    .planTitle(entity.getPlanTitle())
                    .planTraffic(entity.getPlanTraffic())
                    .planHome(entity.getPlanHome())
                    .coupleID(coupleid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
