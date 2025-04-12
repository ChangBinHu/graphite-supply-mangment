package com.psq.supply.controller;

import com.mysql.cj.util.StringUtils;
import com.psq.supply.entity.OrderRecord;
import com.psq.supply.entity.PageData;
import com.psq.supply.repository.OrderRecordRepository;
import com.psq.supply.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:27
 **/
@RestController
@RequestMapping("/orders")
public class OrderRecordController {

    private final OrderRecordRepository orderRecordRepository;

    @Autowired
    private OrderRecordService orderRecordService;

    public OrderRecordController(OrderRecordRepository orderRecordRepository) {
        this.orderRecordRepository = orderRecordRepository;
    }

    @GetMapping
    public PageData<OrderRecord>getInventories(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<OrderRecord> users = orderRecordService.getAllOrder(pageNumber, pageSize);
        PageData<OrderRecord> pageData = getOrderRecordPageData(users);

        return pageData;
    }

    private PageData<OrderRecord> getOrderRecordPageData(Page<OrderRecord> users) {
        PageData<OrderRecord> pageData = new PageData();
        pageData.setData(users.getContent());
        pageData.setTotalNum(users.getTotalElements());
        pageData.setHeaders(Arrays.asList("订单编号", "客户", "日期", "金额", "当前位置","状态", "操作"));
        return pageData;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countOrder(@RequestParam(defaultValue = "") String status) {
        return ResponseEntity.ok(orderRecordService.countOrderStatus(status));
    }

    @PostMapping
    public OrderRecord createInventory(@RequestBody OrderRecord orderRecord) {
        if (Objects.nonNull(orderRecord) && (StringUtils.isNullOrEmpty(orderRecord.getId()))) {
            orderRecord.setId(String.valueOf(System.currentTimeMillis()));
            return orderRecordRepository.save(orderRecord);
        } else {
            return orderRecordRepository.save(orderRecord);
        }

    }


    @GetMapping("/search")
    public PageData<OrderRecord> searchOrderRecord(@RequestParam(defaultValue = "") String id,
                                               @RequestParam(defaultValue = "") String status) {
        Page<OrderRecord> orderRecords = orderRecordService.searchByIdAndStatus(id, status);

        PageData<OrderRecord> pageData = getOrderRecordPageData(orderRecords);

        return pageData;

    }

    @GetMapping("/{id}")
    public OrderRecord getInventory(@PathVariable String id) {
        return orderRecordRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable String id) {
        orderRecordRepository.deleteById(id);
    }
}
