package com.example.order.service;

import com.example.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();
    OrderDTO saveOrder(OrderDTO OrderDTO);
    OrderDTO updateOrder(OrderDTO OrderDTO);
    String deleteOrder(Long orderId);
    OrderDTO getOrderById(Long orderId);
}
