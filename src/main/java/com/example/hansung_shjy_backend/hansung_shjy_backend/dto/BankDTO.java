package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class BankDTO {

    private Integer bankID;
    private Date bankDate;
    private Boolean payMethod;
    private String bankTitle;
    private Integer money;
    private Integer userID;

    public static BankDTO toDTO(Bank entity) {

        Integer userid = null;

        if(entity.getUserID() != null) userid = entity.getUserID().getUserID();

        try {
            return BankDTO.builder()
                    .bankID(entity.getBankID())
                    .bankDate(entity.getBankDate())
                    .payMethod(entity.getPayMethod())
                    .bankTitle(entity.getBankTitle())
                    .money(entity.getMoney())
                    .userID(userid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
