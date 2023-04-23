package com.assam.mestutos.service.mapper;

import com.assam.mestutos.domain.Category;
import com.assam.mestutos.service.dto.CategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDTO categoryDTO);
    List<Category> toEntity(List<CategoryDTO> categoryDTO);
    CategoryDTO toDTO(Category category);
    List<CategoryDTO> toDTO(List<Category> category);
}
