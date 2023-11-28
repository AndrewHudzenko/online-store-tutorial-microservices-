package com.andersen.productservice.service.mapper;

import com.andersen.productservice.dto.ProductResponseDto;
import com.andersen.productservice.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductResponseDto toDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
