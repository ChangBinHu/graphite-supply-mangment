package com.psq.supply.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {

    private List<T> data;

    private long totalNum;

    private List<String> headers;
}
