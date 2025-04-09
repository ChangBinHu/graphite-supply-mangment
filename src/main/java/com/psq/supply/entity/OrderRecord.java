package com.psq.supply.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:25
 **/
@Entity
@Table(name = "order_record")
public class OrderRecord {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "customer_name")
    private String customer;

    @Column(name = "ship_date")
    private String shipDate;

    @Column(name = "estimated_arrival")
    private String estimatedArrival;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(String estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
