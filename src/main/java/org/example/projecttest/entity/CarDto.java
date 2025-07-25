package org.example.projecttest.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String manufacturer;

    private Integer seatCount;

    private String type;

    private CarBrand carBrand;
}
