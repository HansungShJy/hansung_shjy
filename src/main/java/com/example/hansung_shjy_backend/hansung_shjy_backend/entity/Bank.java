package com.example.hansung_shjy_backend.hansung_shjy_backend.entity;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.BankDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    //foreign key
    @ManyToOne(cascade = CascadeType.ALL)  // Many: Bank, One: User
    @JoinColumn(name = "userID", referencedColumnName = "userID", foreignKey = @ForeignKey(name = "userID_Bank"))
    private User userID;

    public void setUserID(User user) {
        this.userID = user;
    }

    public static Bank toEntity(BankDTO dto) {
        Bank bank =  Bank.builder()
                .bankID(dto.getBankID())
                .bankDate(dto.getBankDate())
                .payMethod(dto.getPayMethod())
                .bankTitle(dto.getBankTitle())
                .money(dto.getMoney())
                .build();

        if (dto.getUserID() != null) {
            User user = new User();
            user.setUserID(dto.getUserID());
            bank.setUserID(user);
        }

        return bank;
    }
}
