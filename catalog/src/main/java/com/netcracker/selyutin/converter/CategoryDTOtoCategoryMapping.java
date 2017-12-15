package com.netcracker.selyutin.converter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import com.netcracker.selyutin.dto.CategoryDTO;
import com.netcracker.selyutin.entity.Category;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOtoCategoryMapping extends ConverterConfigurerSupport<CategoryDTO, Category> {

    @Override
    protected Converter<CategoryDTO, Category> converter() {
        return new AbstractConverter<CategoryDTO, Category>() {
            @Override
            protected Category convert(CategoryDTO categoryDTO) {
                Category category = new Category();
                category.setId(categoryDTO.getId());
                category.setName(categoryDTO.getName());
                return category;
            }
        };
    }
}
