package com.andersen.inventoryservice.service.impl;

import com.andersen.inventoryservice.dto.InventoryResponseDto;
import com.andersen.inventoryservice.repository.InventoryRepository;
import com.andersen.inventoryservice.service.InventoryService;
import com.andersen.inventoryservice.service.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventoryMapper::toDto)
                .toList();
    }
}
