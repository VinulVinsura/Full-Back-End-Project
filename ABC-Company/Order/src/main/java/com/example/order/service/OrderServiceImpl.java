package com.example.order.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.Response;
import com.example.order.entity.Order;
import com.example.order.repostory.OrderRepo;
import com.example.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepo orderRepo;
    private final WebClient webClient;
    private final OrderServiceClient orderServiceClient;
    public List<OrderDTO> getAllOrders() {
        List<Order>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public Response saveOrder(OrderDTO orderDTO) {
        try {

            int itemId=orderDTO.getItemId();

            //using webClient
            InventoryDTO inventoryDTO = webClient.get()
                    .uri("http://localhost:8080/api/inventory/item/"+itemId)
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();



            if(inventoryDTO==null){
                return new Response("Item not found",null);
            }

            if(inventoryDTO.getQuantity()>0){
                int productId=inventoryDTO.getProductId();
                //using FeignClient
                ProductDto product = orderServiceClient.getProductById(productId);
//                System.err.println("===================================");
//                System.out.println(product);
                if(product.getForSale()==0){
                    return new Response("Product is not ready for sale",null);
                }
                Order save = orderRepo.save(modelMapper.map(orderDTO, Order.class));
                return  new Response("Order save success",modelMapper.map(save, OrderDTO.class));
            }else {
                return new Response("Item not available try later",null);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return new Response("Error",e.getMessage());
        }
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
