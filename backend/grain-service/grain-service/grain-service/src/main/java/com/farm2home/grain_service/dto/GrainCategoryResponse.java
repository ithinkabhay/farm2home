package com.farm2home.grain_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GrainCategoryResponse {
    private Long id;
    private String code;
    private String nameEn;
    private String nameHi;
}
