package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.PlanDetail;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class PlanDetailDTO {
    private Integer planDetailID;
    private Integer planDayNumber;
    private Integer planNumber;
    private String planLocation;
    private Integer planPrice;
    private Date planStartTime;
    private Date planEndTime;
    private Boolean planCheck;
    private Integer planID;

    public static PlanDetailDTO toDTO(PlanDetail entity) {

        Integer planid = null;

        if(entity.getPlanid() != null) planid = entity.getPlanid().getPlanID();

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
