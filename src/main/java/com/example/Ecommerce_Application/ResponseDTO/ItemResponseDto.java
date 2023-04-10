package com.example.Ecommerce_Application.ResponseDTO;

import com.example.Ecommerce_Application.Enum.ProductCategory;
import com.example.Ecommerce_Application.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {

    private String productName;

    private int price;

    private ProductCategory productCategory;

    private ProductStatus productStatus;
}