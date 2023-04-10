package com.example.Ecommerce_Application.Service;

import com.example.Ecommerce_Application.Convertor.SellerConvertor;
import com.example.Ecommerce_Application.Model.Seller;
import com.example.Ecommerce_Application.Repository.SellerRepository;
import com.example.Ecommerce_Application.RequestDTO.SellerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepo;

    public String addSeller(SellerRequestDto sellerRequestDto){

        Seller seller = SellerConvertor.SellerRequestDtoToSeller(sellerRequestDto);
        sellerRepo.save(seller);

        return "Congrats! You are a Seller !!!";
    }
}