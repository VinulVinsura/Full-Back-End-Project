package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.entity.Order;
import com.example.order.repostory.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepo orderRepo;
    public List<OrderDTO> getAllOrders() {
        List<Order>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order save = orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return modelMapper.map(save, OrderDTO.class);
    }

    public OrderDTO updateOrder(OrderDTO OrderDTO) {
        Order save = orderRepo.save(modelMapper.map(OrderDTO, Order.class));
        return modelMapper.map(save,com.example.order.dto.OrderDTO.class);
    }

    public String deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted";
    }

    public OrderDTO getOrderById(Long orderId) {
        Optional<Order> byId = orderRepo.findById(orderId);
        return modelMapper.map(byId.get(), OrderDTO.class);
    }
}
