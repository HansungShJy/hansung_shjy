package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class PlanDTO {
    private Integer planID;
    private Date planStartDate;
    private Date planEndDate;
    private String planTitle;
    private Integer planTraffic;
    private String planHome;
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
