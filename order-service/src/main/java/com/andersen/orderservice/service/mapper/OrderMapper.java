package com.andersen.orderservice.service.mapper;

import com.andersen.orderservice.dto.OrderRequestDto;
import com.andersen.orderservice.dto.OrderResponseDto;
import com.andersen.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderMapper {
    public OrderResponseDto toDto(Order order) {
        return OrderResponseDto.builder()
                .build();
    }

    public Order toModel(OrderRequestDto orderRequestDto) {
        orderRequestDto.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> )

        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequestDto.getOrderLineItemsDtoList())
                .build();
    }
}
