package org.example.projecttest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBrand {
    private String brandName;
    private String brandCode;
    private String date;
    private String price;
}
