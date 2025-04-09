package com.psq.supply.controller;

import com.mysql.cj.util.StringUtils;
import com.psq.supply.entity.PageData;
import com.psq.supply.entity.Supplier;
import com.psq.supply.repository.SupplierRepository;
import com.psq.supply.service.SupplierService;
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
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierRepository supplierRepository;

    @Autowired
    private SupplierService supplierService;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public PageData<Supplier>  getInventories(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Supplier> users = supplierService.getAllSupplier(pageNumber, pageSize);
        PageData<Supplier> pageData = new PageData();
        pageData.setData(users.getContent());
        pageData.setTotalNum(users.getTotalElements());
        pageData.setHeaders(Arrays.asList( "供应商ID", "供应商名称", "联系人", "联系电话", "提供产品数", "合作关系", "操作"));

        return pageData;
    }

    @PostMapping
    public Supplier createInventory(@RequestBody Supplier supplier) {
        if (Objects.nonNull(supplier) && (StringUtils.isNullOrEmpty(supplier.getId()))) {
            supplier.setId(String.valueOf(System.currentTimeMillis()));
            return supplierRepository.save(supplier);
        } else {
            return supplierRepository.save(supplier);
        }
    }

    @GetMapping("/{id}")
    public Supplier getInventory(@PathVariable String id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable String id) {
        supplierRepository.deleteById(id);
    }
}
