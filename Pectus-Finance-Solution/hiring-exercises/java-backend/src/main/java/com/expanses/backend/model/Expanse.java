package com.expanses.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expanse {

    @Id
    private Long id;

    private String departments;

    private String projectName;

    private Double amount;

    private LocalDate date;

    private String memberName;

}
