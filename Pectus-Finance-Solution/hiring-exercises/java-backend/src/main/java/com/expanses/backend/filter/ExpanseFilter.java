package com.expanses.backend.filter;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExpanseFilter {

    private Double amount;
    private String memberName;
    private String sort;
    private String order;
    private List<String> fields;
}
