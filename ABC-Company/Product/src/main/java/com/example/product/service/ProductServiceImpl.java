package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.dto.Response;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDto>>(){}.getType());
    }

    public ProductDto saveProduct(ProductDto productDTO) {
        Product save = productRepo.save(modelMapper.map(productDTO, Product.class));
        return modelMapper.map(save, ProductDto.class);
    }

    public ProductDto updateProduct(ProductDto productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public String deleteProduct(Long id) {
        productRepo.deleteById(id);
        return "Product deleted";
    }

    public ProductDto getProductById(Integer productId) {
        Product product = productRepo.findByProductId(productId);
        return modelMapper.map(product, ProductDto.class);
    }
}
