package com.example.inventory.service;

import com.example.inventory.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {

    List<InventoryDTO> getAllItems();
    InventoryDTO saveItem(InventoryDTO inventoryDTO);
    InventoryDTO updateItem(InventoryDTO inventoryDTO);
    String deleteItem(Integer itemId);
    InventoryDTO getItemById(Integer itemId);

}
