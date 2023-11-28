package com.andersen.orderservice.service.mapper;

import com.andersen.orderservice.dto.OrderRequestDto;
import com.andersen.orderservice.dto.OrderResponseDto;
import com.andersen.orderservice.model.Order;
import com.andersen.orderservice.model.OrderLineItems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderLineItemsMapper orderLineItemsMapper;

    public OrderResponseDto toDto(Order order) {
        return OrderResponseDto.builder()
                .build();
    }

    public Order toModel(OrderRequestDto orderRequestDto) {
        List<OrderLineItems> orderLineItems = orderRequestDto.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsMapper::toModel)
                .toList();

        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItems)
                .build();
    }
}
