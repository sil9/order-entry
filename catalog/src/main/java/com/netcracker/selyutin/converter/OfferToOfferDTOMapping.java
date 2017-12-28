package com.netcracker.selyutin.converter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import com.netcracker.selyutin.dto.OfferDTO;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class OfferToOfferDTOMapping extends ConverterConfigurerSupport<Offer, OfferDTO> {

    @Override
    protected Converter<Offer, OfferDTO> converter() {
        return new AbstractConverter<Offer, OfferDTO>() {
            @Override
            protected OfferDTO convert(Offer offer) {
                OfferDTO offerDTO = new OfferDTO();
                offerDTO.setId(offer.getId());
                offerDTO.setName(offer.getName());
                offerDTO.setDescription(offer.getDescription());
                offerDTO.setAvailability(offer.isAvailability());
                offerDTO.setFullDescription(offer.getFullDescription());
                Category categoryName = offer.getCategory();
                if (categoryName != null) {
                    offerDTO.setCategoryName(categoryName.getName());
                }
                if (offer.getPrice() != null) {
                    offerDTO.setPrice(offer.getPrice().getValue());
                }
                offerDTO.setImageUrl(offer.getImageUrl());
                return offerDTO;
            }
        };
    }
}
