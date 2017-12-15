package com.netcracker.selyutin.converter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import com.netcracker.selyutin.dto.TagDTO;
import com.netcracker.selyutin.entity.Tag;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class TagDTOtoTagMapping extends ConverterConfigurerSupport<TagDTO, Tag> {

    @Override
    protected Converter<TagDTO, Tag> converter() {
        return new AbstractConverter<TagDTO, Tag>() {
            @Override
            protected Tag convert(TagDTO tagDTO) {
                Tag tag = new Tag();
                tag.setId(tagDTO.getId());
                tag.setSign(tagDTO.getSign());
                return tag;
            }
        };
    }
}
