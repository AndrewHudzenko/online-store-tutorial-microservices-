package com.andersen.inventoryservice.service;

import com.andersen.inventoryservice.dto.InventoryResponseDto;

import java.util.List;

public interface InventoryService {
    List<InventoryResponseDto> isInStock(List<String> skuCode);
}
