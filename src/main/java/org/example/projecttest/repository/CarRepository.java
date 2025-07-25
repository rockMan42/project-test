package org.example.projecttest.repository;

import org.example.projecttest.entity.Brand;
import org.example.projecttest.entity.Car;
import org.example.projecttest.entity.CarBrand;
import org.example.projecttest.entity.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CarRepository {
        
    @Mapping(source = "numberOfSeats",target = "seatCount")
    @Mapping(source = "make",target = "manufacturer")
    @Mapping(target = "type",constant = "hello") //如何没有source不能用defaulValue
    CarDto carToCarDto(Car car);

    @Mappings({
    @Mapping(source = "numberOfSeats",target = "seatCount"),
    @Mapping(source = "make",target = "manufacturer"),
    @Mapping(target = "type",constant = "hello"),
    @Mapping(source = "brandi",target = "carBrand")
    })
    CarDto carToCarDtoV2(Car car);

    
    @Mappings({
    @Mapping(source = "brand",target = "brandName"),
    @Mapping(source = "code",ignore = true,target = "brandCode"),
    @Mapping(source = "localDateTime",target = "date",dateFormat = "yyyy-MM-dd HH:mm:ss"),
    @Mapping(source = "bigDecimal",target = "price",numberFormat = "0.00")
    })
    CarBrand brand2CarBrand(Brand brand);
}
