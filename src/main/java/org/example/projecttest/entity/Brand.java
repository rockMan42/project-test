package org.example.projecttest.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    private String brand;
    private Integer code;
    private LocalDateTime localDateTime;
    private BigDecimal bigDecimal;
}
