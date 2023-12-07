package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plan")
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Entity

// plan 테이블
public class Plan implements Serializable {

    @Id
    @GeneratedValue     //auto_increment
    @Column(name = "planID")
    private Integer planID;

    @Column(name = "planStartDate")
    private Date planStartDate;

    @Column(name = "planEndDate")
    private Date planEndDate;

    @Column(name = "planTitle")
    private String planTitle;

    @Column(name = "planTraffic")
    private Integer planTraffic;

    @Column(name = "planHome")
    private String planHome;

    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: Plan, One: User
    @JsonManagedReference
    @JoinColumn(name = "userID", referencedColumnName = "userID", foreignKey = @ForeignKey(name = "userID_Plan"))
    private User userID;

    @OneToMany(mappedBy = "planID", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<PlanDetail> planDetails;


    public static Plan toEntity(PlanDTO dto) {
        return Plan.builder()
                .planID(dto.getPlanID())
                .planStartDate(dto.getPlanStartDate())
                .planEndDate(dto.getPlanEndDate())
                .planTitle(dto.getPlanTitle())
                .planTraffic(dto.getPlanTraffic())
                .planHome(dto.getPlanHome())
                .build();
    }
}
