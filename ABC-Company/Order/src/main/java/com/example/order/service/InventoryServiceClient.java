package com.example.order.service;

import com.example.inventory.dto.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "Inventory")
@Service
public interface InventoryServiceClient {

    @GetMapping("/api/inventory/item/{itemId}")
    InventoryDTO getItemByItemId(@PathVariable Integer itemId);
}
