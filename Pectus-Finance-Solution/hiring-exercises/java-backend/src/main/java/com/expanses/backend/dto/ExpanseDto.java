package com.expanses.backend.dto;

import com.expanses.backend.model.Expanse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpanseDto {

    private String departments;

    private String projectName;

    private Double amount;

    private LocalDate date;

    private String memberName;
}
