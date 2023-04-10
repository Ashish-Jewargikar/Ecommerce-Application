package com.example.Ecommerce_Application.Service;

import com.example.Ecommerce_Application.Convertor.ProductConvertor;
import com.example.Ecommerce_Application.Enum.ProductCategory;
import com.example.Ecommerce_Application.Exception.SellerNotFoundException;
import com.example.Ecommerce_Application.Model.Product;
import com.example.Ecommerce_Application.Model.Seller;
import com.example.Ecommerce_Application.Repository.ProductRepository;
import com.example.Ecommerce_Application.Repository.SellerRepository;
import com.example.Ecommerce_Application.RequestDTO.ProductRequestDto;
import com.example.Ecommerce_Application.ResponseDTO.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {

        Seller seller;

        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch(Exception e){
            throw new SellerNotFoundException("Invalid Seller Id");
        }

        Product product = ProductConvertor.productRequestDtotoProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);

        // saved the seller and product - parent and child
        sellerRepository.save(seller);

        // prepare response
        ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
        return productResponseDto;
    }

    public List<ProductResponseDto> getProductsByCategory(ProductCategory productCategory){

        List<Product> products = productRepository.findAllByProductCategory(productCategory);

        // prepare a list of response dtos
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }

        return productResponseDtos;
    }
}