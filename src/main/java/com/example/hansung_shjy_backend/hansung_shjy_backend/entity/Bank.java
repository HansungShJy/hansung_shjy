package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.BankDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank")
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Entity

// bank 테이블
public class Bank implements Serializable {

    @Id
    @GeneratedValue     //auto_increment
    @Column(name = "bankID")
    private Integer bankID;

    @Column(name = "bankDate")
    private Date bankDate;

    @Column(name = "payMethod")
    private Boolean payMethod;

    @Column(name = "bankTitle")
    private String bankTitle;

    @Column(name = "money")
    private Integer money;

    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: Bank, One: User
    @JoinColumn(name = "userID", referencedColumnName = "userID", foreignKey = @ForeignKey(name = "userID_Bank"))
    private User userID;


    public static Bank toEntity(BankDTO dto) {
        return Bank.builder()
                .bankID(dto.getBankID())
                .bankDate(dto.getBankDate())
                .payMethod(dto.getPayMethod())
                .bankTitle(dto.getBankTitle())
                .money(dto.getMoney())
                .build();
    }
}
