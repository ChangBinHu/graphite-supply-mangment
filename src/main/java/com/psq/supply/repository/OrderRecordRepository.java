package com.psq.supply.repository;

import com.psq.supply.entity.OrderRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:26
 **/
public interface OrderRecordRepository extends JpaRepository<OrderRecord, String> {

    // 默认分页查询（返回全部数据的分页结果）
    Page<OrderRecord> findAll(Pageable pageable);

    // 按条件统计（示例：统计状态为 active 的用户数）
    long countByStatus(String status);

}
