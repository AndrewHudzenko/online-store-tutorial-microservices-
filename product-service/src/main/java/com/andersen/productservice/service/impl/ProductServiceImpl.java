package com.andersen.productservice.service.impl;

import com.andersen.productservice.dto.ProductRequestDto;
import com.andersen.productservice.dto.ProductResponseDto;
import com.andersen.productservice.model.Product;
import com.andersen.productservice.repository.ProductRepository;
import com.andersen.productservice.service.ProductService;
import com.andersen.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product {} is saved", product.getId());

        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDto getById(String id) {
        return productMapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found!")));
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}
