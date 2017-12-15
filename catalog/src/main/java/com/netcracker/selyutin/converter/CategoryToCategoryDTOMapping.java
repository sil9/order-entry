package com.netcracker.selyutin.converter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import com.netcracker.selyutin.dto.CategoryDTO;
import com.netcracker.selyutin.entity.Category;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDTOMapping extends ConverterConfigurerSupport<Category, CategoryDTO> {

    @Override
    protected Converter<Category, CategoryDTO> converter() {
        return new AbstractConverter<Category, CategoryDTO>() {
            @Override
            protected CategoryDTO convert(Category category) {
                return new CategoryDTO(category.getId(), category.getName());
            }
        };
    }
}
