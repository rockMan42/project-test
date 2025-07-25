package org.example.projecttest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String make;
    
    private Integer numberOfSeats;

    private Brand brandi;
}
