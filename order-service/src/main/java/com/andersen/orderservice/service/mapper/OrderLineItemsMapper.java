package com.andersen.orderservice.service.mapper;

import com.andersen.orderservice.dto.OrderLineItemsDto;
import com.andersen.orderservice.model.OrderLineItems;
import org.springframework.stereotype.Service;

@Service
public class OrderLineItemsMapper {
    public OrderLineItems toModel (OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
