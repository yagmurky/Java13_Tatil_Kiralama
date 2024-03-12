package com.yagmur.service;

import com.yagmur.dto.request.CategoryRequestDto;
import com.yagmur.entity.Category;
import com.yagmur.exception.ErrorType;
import com.yagmur.exception.HolidayException;
import com.yagmur.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //save
    public Category save(CategoryRequestDto categoryRequestDto){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryRequestDto.getParentId());
        if(optionalCategory.isPresent()){
            throw new HolidayException(ErrorType.CATEGORY_ALREADY_EXISTS);
        }
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .parentId(categoryRequestDto.getParentId())
                .build();
        return categoryRepository.save(category);
    }


}
