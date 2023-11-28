package com.andersen.inventoryservice.service.mapper;

import com.andersen.inventoryservice.dto.InventoryResponseDto;
import com.andersen.inventoryservice.model.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {
    public InventoryResponseDto toDto(Inventory inventory) {
        return InventoryResponseDto.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}
