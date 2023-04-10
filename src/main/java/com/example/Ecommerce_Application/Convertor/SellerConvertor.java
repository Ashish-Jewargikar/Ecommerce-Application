package com.example.Ecommerce_Application.Convertor;

import com.example.Ecommerce_Application.Model.Seller;
import com.example.Ecommerce_Application.RequestDTO.SellerRequestDto;
import lombok.experimental.UtilityClass;


@UtilityClass
public class SellerConvertor {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }
}
