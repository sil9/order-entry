package com.netcracker.selyutin.converter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import com.netcracker.selyutin.dto.OfferDTO;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Price;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class OfferDTOtoOfferMapping extends ConverterConfigurerSupport<OfferDTO, Offer> {

    @Override
    protected Converter<OfferDTO, Offer> converter() {
        return new AbstractConverter<OfferDTO, Offer>() {
            @Override
            protected Offer convert(OfferDTO offerDTO) {
                Offer offer = new Offer();
                offer.setId(offerDTO.getId());
                offer.setName(offerDTO.getName());
                offer.setDescription(offerDTO.getDescription());
                offer.setAvailability(offerDTO.isAvailability());
                offer.setImageUrl(offerDTO.getImageUrl());
                offer.setPrice(new Price(offerDTO.getPrice()));
                return offer;
            }
        };
    }
}
