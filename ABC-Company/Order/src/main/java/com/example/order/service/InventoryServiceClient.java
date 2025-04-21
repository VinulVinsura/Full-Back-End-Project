package com.example.order.service;




//import com.example.Common.dto.InventoryDTO;
import com.example.order.dto.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "Inventory")
@Service
public interface InventoryServiceClient {

   @GetMapping("/inventory-service/api/inventory/item/{itemId}")
   InventoryDTO getItemByItemId(@PathVariable Integer itemId);
}
