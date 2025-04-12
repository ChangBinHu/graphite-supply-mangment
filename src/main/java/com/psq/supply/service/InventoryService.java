package com.psq.supply.service;

import com.psq.supply.entity.Inventory;
import com.psq.supply.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psq
 * @description
 * @create 2025-04-02 20:11
 **/
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Page<Inventory> getAllInventory(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        return inventoryRepository.findAll(pageable);
    }

    public Page<Inventory> searchByIdAndName(String id, String name) {
        List<Inventory> inventories = inventoryRepository.findAll();
        if (!StringUtils.isEmpty(id)) {
            inventories = inventories.stream()
                    .filter(inventory -> inventory.getId().equals(id)).collect(Collectors.toList());
        }

        if (!StringUtils.isEmpty(name)) {
            inventories = inventories.stream().filter(inventory -> inventory.getName().contains(name)).collect(Collectors.toList());
        }
        Page page = new PageImpl(inventories);

        return page;
    }
}
