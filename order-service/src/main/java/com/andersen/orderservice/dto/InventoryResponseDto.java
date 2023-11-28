package com.andersen.orderservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {
    private String skuCode;
    private Boolean isInStock;
}
