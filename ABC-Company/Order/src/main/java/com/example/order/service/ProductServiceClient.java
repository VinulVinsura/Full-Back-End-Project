package com.example.order.service;


import com.example.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name ="Product")
@Service
public interface ProductServiceClient {

    @GetMapping("/api/product/product/{productId}")
    ProductDto getProductById(@PathVariable Integer productId);

}
