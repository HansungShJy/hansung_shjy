package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.CoupleDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "couple")
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Entity

// Couple 테이블
public class Couple implements Serializable {
    @Id
    @GeneratedValue   //auto_increment
    @Column(name = "coupleID")
    private Integer coupleID;

//    @OneToMany(mappedBy = "couple", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonBackReference
//    @Column(name = "coupleUser")
//    private List<User> couple;

    //foreign key (me, other)
    @ManyToOne
    @JoinColumn(name = "me", referencedColumnName = "userID")
    @JsonBackReference
    private User me;

    @ManyToOne
    @JoinColumn(name = "other", referencedColumnName = "userID")
    @JsonBackReference
    private User other;

    public static Couple toEntity(CoupleDTO dto) {
        return Couple.builder()
                .coupleID(dto.getCoupleID())
                .build();
    }
}
