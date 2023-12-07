package com.example.hansung_shjy_backend.hansung_shjy_backend.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PlanRequest {
    @JsonProperty("planDTO")
    private PlanDTO planDTO;

    @JsonProperty("planDetailDTO")
    private PlanDetailDTO planDetailDTO;
}
