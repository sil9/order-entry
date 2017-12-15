package com.netcracker.selyutin.converter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import com.netcracker.selyutin.dto.TagDTO;
import com.netcracker.selyutin.entity.Tag;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class TagToTagDTOMapping extends ConverterConfigurerSupport<Tag, TagDTO> {

    @Override
    protected Converter<Tag, TagDTO> converter() {
        return new AbstractConverter<Tag, TagDTO>() {
            @Override
            protected TagDTO convert(Tag tag) {
                return new TagDTO(tag.getId(), tag.getSign());
            }
        };
    }
}
