package com.example.order.service;





import com.example.Common.dto.InventoryDTO;
import com.example.Common.dto.ProductDto;
import com.example.order.dto.MessageDto;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.Response;
import com.example.order.entity.Order;
import com.example.order.kafka.Producer;
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

    private final ProductServiceClient productServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final Producer producer;
    public List<OrderDTO> getAllOrders() {
        List<Order>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public Response saveOrder(OrderDTO orderDTO) {
        try {

            int itemId=orderDTO.getItemId();

//            //using webClient
//            InventoryDTO inventoryDTO = webClient.get()
//                    .uri("http://Inventory/api/inventory/item/"+itemId)
//                    .retrieve()
//                    .bodyToMono(InventoryDTO.class)
//                    .block();

            InventoryDTO inventoryDTO = inventoryServiceClient.getItemByItemId(itemId);


            if(inventoryDTO==null){
                return new Response("Item not found",null);
            }

            if(inventoryDTO.getQuantity()>0){
                int productId=inventoryDTO.getProductId();
                //using FeignClient
                ProductDto product = productServiceClient.getProductById(productId);

                if(product.getForSale()==0){
                    return new Response("Product is not ready for sale",null);
                }
                Order save = orderRepo.save(modelMapper.map(orderDTO, Order.class));

                producer.sendMessage(new MessageDto("Order Plased","Success"));
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
