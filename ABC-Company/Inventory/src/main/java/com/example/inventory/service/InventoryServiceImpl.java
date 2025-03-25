package com.example.inventory.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.entity.Inventory;
import com.example.inventory.repository.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepo inventoryRepo;

    private final ModelMapper modelMapper;

    public List<InventoryDTO> getAllItems() {
        List<Inventory>itemList = inventoryRepo.findAll();
        return modelMapper.map(itemList, new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    public InventoryDTO saveItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public InventoryDTO updateItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public String deleteItem(Integer itemId) {
        inventoryRepo.deleteByItemId(itemId);
        return "Item deleted";
    }

    public InventoryDTO getItemById(Integer itemId) {
        Inventory item = inventoryRepo.findByItemId(itemId);
        return modelMapper.map(item, InventoryDTO.class);
    }
}
