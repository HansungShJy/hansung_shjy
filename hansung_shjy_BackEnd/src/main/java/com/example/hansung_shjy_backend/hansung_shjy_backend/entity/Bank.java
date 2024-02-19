package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.BankDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate bankDate;

    @Column(name = "payMethod")
    private Boolean payMethod;

    @Column(name = "bankTitle")
    private String bankTitle;

    @Column(name = "money")
    private Integer money;

    @ManyToOne(cascade = CascadeType.ALL)  // Many: User, One: Couple
    @JsonManagedReference
    @JoinColumn(name = "coupleID", referencedColumnName = "coupleID", foreignKey = @ForeignKey(name = "coupleID_bank"))
    private Couple couple;

    public static Bank toEntity(BankDTO dto) {
        Bank bank =  Bank.builder()
                .bankID(dto.getBankID())
                .bankDate(dto.getBankDate())
                .payMethod(dto.getPayMethod())
                .bankTitle(dto.getBankTitle())
                .money(dto.getMoney())
                .build();


        return bank;
    }
}
