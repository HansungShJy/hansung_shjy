package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class CoupleDTO {

    private Integer coupleID;
    private Integer me;
    private Integer other;

    public static CoupleDTO toDTO(Couple entity) {
        Integer myid = (entity.getMe() != null) ? entity.getMe().getUserID() : null;
        Integer otherid = (entity.getOther() != null) ? entity.getOther().getUserID() : null;

        try {
            return CoupleDTO.builder()
                    .coupleID(entity.getCoupleID())
                    .me(myid)
                    .other(otherid)
                    .build();
        } catch (Error e) {
            return null;
        }
    }
}
