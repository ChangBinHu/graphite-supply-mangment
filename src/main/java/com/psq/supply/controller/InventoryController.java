package com.psq.supply.controller;

import com.mysql.cj.util.StringUtils;
import com.psq.supply.entity.Inventory;
import com.psq.supply.entity.PageData;
import com.psq.supply.repository.InventoryRepository;
import com.psq.supply.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:27
 **/
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @Autowired
    private InventoryService inventoryService;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping
    public PageData<Inventory> getInventories(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Inventory> users = inventoryService.getAllInventory(pageNumber, pageSize);

        PageData<Inventory> pageData = new PageData();
        pageData.setData(users.getContent());
        pageData.setTotalNum(users.getTotalElements());
        pageData.setHeaders(Arrays.asList("产品编号", "产品名称", "类别", "数量", "单价", "最后更新", "操作"));

        return pageData;
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {

        if (Objects.nonNull(inventory) && (StringUtils.isNullOrEmpty(inventory.getId()))) {
            inventory.setId(String.valueOf(System.currentTimeMillis()));
//            inventory.setLastUpdated(String.valueOf(new Date()));
//            inventory.setCreatedAt(String.valueOf(new Date()));
            return inventoryRepository.save(inventory);
        } else {
            Inventory inventoryDB = inventoryRepository.findById(inventory.getId()).get();
//            inventory.setCreatedAt(inventoryDB.getCreatedAt());
//            inventory.setLastUpdated(String.valueOf(System.currentTimeMillis()));
            return inventoryRepository.save(inventory);
        }
    }

    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable String id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable String id) {
        inventoryRepository.deleteById(id);
    }
}
