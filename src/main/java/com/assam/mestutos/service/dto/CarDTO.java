package com.assam.mestutos.service.dto;

import com.assam.mestutos.domain.enumeration.ModelEnum;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String name;
    private String description;
    private CategoryDTO categoryDTO;
    private ModelEnum model;
}
