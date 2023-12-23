package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class BankDTO {

    private Integer bankID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bankDate;
    private Boolean payMethod;
    private String bankTitle;
    private Integer money;
    private Integer coupleID;

    public static BankDTO toDTO(Bank entity) {

        Integer coupleid = (entity.getCouple() != null) ? entity.getCouple().getCoupleID() : null;

        try {
            return BankDTO.builder()
                    .bankID(entity.getBankID())
                    .bankDate(entity.getBankDate())
                    .payMethod(entity.getPayMethod())
                    .bankTitle(entity.getBankTitle())
                    .money(entity.getMoney())
                    .coupleID(coupleid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
