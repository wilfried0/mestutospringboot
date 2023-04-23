package com.assam.mestutos.service;

import com.assam.mestutos.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO carDTO);
    Page<CategoryDTO> getAllCategory(Pageable pageable);
    CategoryDTO getCategoryById(Long id) throws Exception;
    void deleteCategory(Long id) throws Exception;
    CategoryDTO updateCategory(CategoryDTO carDTO);
}
