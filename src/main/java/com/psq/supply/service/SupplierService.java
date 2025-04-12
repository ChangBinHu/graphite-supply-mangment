package com.psq.supply.service;

import com.psq.supply.entity.Supplier;
import com.psq.supply.repository.SupplierRepository;
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
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> getAllSupplier(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        return supplierRepository.findAll(pageable);
    }

    public Page<Supplier> searchByName(String name) {
        List<Supplier> suppliers = supplierRepository.findAll();


        if (!StringUtils.isEmpty(name)) {
            suppliers = suppliers.stream().filter(supplier -> supplier.getName().contains(name)).collect(Collectors.toList());
        }
        Page page = new PageImpl(suppliers);

        return page;
    }
}
