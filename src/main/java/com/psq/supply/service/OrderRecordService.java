package com.psq.supply.service;

import com.psq.supply.entity.OrderRecord;
import com.psq.supply.repository.OrderRecordRepository;
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
public class OrderRecordService {

    @Autowired
    private OrderRecordRepository orderRecordRepository;

    public Page<OrderRecord> getAllOrder(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        return orderRecordRepository.findAll(pageable);
    }

    public long countOrderStatus(String status) {
        return orderRecordRepository.countByStatus(status);
    }

    public Page<OrderRecord> searchByIdAndStatus(String id, String status) {

        List<OrderRecord> orderRecords = orderRecordRepository.findAll();
        if (!StringUtils.isEmpty(id)) {
            orderRecords = orderRecords.stream()
                    .filter(orderRecord -> orderRecord.getId().equals(id)).collect(Collectors.toList());
        }

        if (!StringUtils.isEmpty(status)) {
            orderRecords = orderRecords.stream().filter(orderRecord -> orderRecord.getStatus().equals(status)).collect(Collectors.toList());
        }
        Page page = new PageImpl(orderRecords);

        return page;
    }
}
