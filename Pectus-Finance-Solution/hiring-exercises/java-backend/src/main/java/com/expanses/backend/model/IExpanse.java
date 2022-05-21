package com.expanses.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface IExpanse {
    String getDepartment();
    String getProjectName();
    String getMemberName();
    Long getSum();
}
