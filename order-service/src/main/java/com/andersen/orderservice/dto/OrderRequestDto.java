package com.andersen.orderservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
