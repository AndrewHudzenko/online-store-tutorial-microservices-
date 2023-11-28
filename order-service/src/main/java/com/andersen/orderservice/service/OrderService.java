package com.andersen.orderservice.service;

import com.andersen.orderservice.dto.OrderRequestDto;
import com.andersen.orderservice.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto create(OrderRequestDto orderRequestDto);
    OrderResponseDto getById(Long id);
    List<OrderResponseDto> getAll();
}
