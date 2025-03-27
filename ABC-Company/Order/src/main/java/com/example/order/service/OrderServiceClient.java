package com.example.order.service;


import com.example.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Order", url = "http://localhost:8082/api/product")
public interface OrderServiceClient {

    @GetMapping("/product/{productId}")
    ProductDto getProductById(@PathVariable Integer productId);

}
