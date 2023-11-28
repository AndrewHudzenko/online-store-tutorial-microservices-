package com.andersen.productservice.service;

import com.andersen.productservice.dto.ProductRequestDto;
import com.andersen.productservice.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto create(ProductRequestDto productRequestDto);
    ProductResponseDto getById(String id);
    List<ProductResponseDto> getAll();
}
