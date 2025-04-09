package com.psq.supply.repository;

import com.psq.supply.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:26
 **/
public interface SupplierRepository extends JpaRepository<Supplier, String> {

    // 默认分页查询（返回全部数据的分页结果）
    Page<Supplier> findAll(Pageable pageable);

}
