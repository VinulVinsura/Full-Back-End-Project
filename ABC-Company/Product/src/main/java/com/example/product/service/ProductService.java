package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.dto.Response;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto saveProduct(ProductDto productDTO);
    ProductDto updateProduct(ProductDto productDTO);
    String deleteProduct(Long id);
    ProductDto getProductById(Integer productId);
}
