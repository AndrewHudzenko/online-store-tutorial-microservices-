package com.andersen.orderservice.service.impl;

import com.andersen.orderservice.dto.InventoryResponseDto;
import com.andersen.orderservice.dto.OrderRequestDto;
import com.andersen.orderservice.dto.OrderResponseDto;
import com.andersen.orderservice.model.Order;
import com.andersen.orderservice.model.OrderLineItems;
import com.andersen.orderservice.repository.OrderRepository;
import com.andersen.orderservice.service.OrderService;
import com.andersen.orderservice.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient webClient;

    @Transactional
    @Override
    public OrderResponseDto create(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.toModel(orderRequestDto);

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        /**
         * Call Inventory service,
         * and create order if product is in stock
         */
        InventoryResponseDto[] inventoryResponseDtoArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseDtoArray).allMatch(InventoryResponseDto::getIsInStock);

        if (allProductsInStock) {
            return orderMapper.toDto(orderRepository.save(order));
        } else {
            throw new RuntimeException("Product is not in stock, please tre again later!");
        }
    }

    @Override
    public OrderResponseDto getById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can not find order with id " + id)));
    }

    @Override
    public List<OrderResponseDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }
}
