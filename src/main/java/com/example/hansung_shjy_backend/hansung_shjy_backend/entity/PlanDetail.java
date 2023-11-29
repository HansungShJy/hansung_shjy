package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDetailDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planDetail")
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Entity

public class PlanDetail implements Serializable {
    @Id
    @GeneratedValue     //auto_increment
    @Column(name = "planDetailID")
    private Integer planDetailID;

    @Column(name = "planDayNumber")
    private Integer planDayNumber;

    @Column(name = "planNumber")
    private Integer planNumber;

    @Column(name = "planLocation")
    private String planLocation;

    @Column(name = "planPrice")
    private Integer planPrice;

    @Column(name = "planStartTime")
    private Date planStartTime;

    @Column(name = "planEndTime")
    private Date planEndTime;

    @Column(name = "planCheck")
    private Boolean planCheck;

    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: Plan, One: PlanDetail
    @JoinColumn(name = "planID", referencedColumnName = "planID", foreignKey = @ForeignKey(name = "planID"))
    private Plan planid;


    public static PlanDetail toEntity(PlanDetailDTO dto) {
        return PlanDetail.builder()
                .planDetailID(dto.getPlanDetailID())
                .planDayNumber(dto.getPlanDayNumber())
                .planNumber(dto.getPlanNumber())
                .planLocation(dto.getPlanLocation())
                .planPrice(dto.getPlanPrice())
                .planStartTime(dto.getPlanStartTime())
                .planEndTime(dto.getPlanEndTime())
                .planCheck(dto.getPlanCheck())
                .build();
    }
}
