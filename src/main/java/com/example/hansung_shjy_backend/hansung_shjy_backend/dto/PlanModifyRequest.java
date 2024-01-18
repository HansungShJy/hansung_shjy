package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class PlanModifyRequest {
    @JsonProperty("planDTO")
    private PlanDTO planDTO;

    @JsonProperty("planDetailDTOs")
    private List<PlanDetailDTO> planDetailDTOs;
}

