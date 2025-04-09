package com.psq.supply.service;

import com.psq.supply.entity.Inventory;
import com.psq.supply.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
