package org.example.projecttest.repository;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.example.projecttest.entity.Brand;
import org.example.projecttest.entity.Car;
import org.example.projecttest.entity.CarBrand;
import org.example.projecttest.entity.CarDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-25T17:53:52+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class CarRepositoryImpl implements CarRepository {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );

    @Override
    public CarDto carToCarDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        carDto.setSeatCount( car.getNumberOfSeats() );
        carDto.setManufacturer( car.getMake() );

        carDto.setType( "hello" );

        return carDto;
    }

    @Override
    public CarDto carToCarDtoV2(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        carDto.setSeatCount( car.getNumberOfSeats() );
        carDto.setManufacturer( car.getMake() );
        carDto.setCarBrand( brand2CarBrand( car.getBrandi() ) );

        carDto.setType( "hello" );

        return carDto;
    }

    @Override
    public CarBrand brand2CarBrand(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        CarBrand carBrand = new CarBrand();

        carBrand.setBrandName( brand.getBrand() );
        if ( brand.getLocalDateTime() != null ) {
            carBrand.setDate( dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168.format( brand.getLocalDateTime() ) );
        }
        if ( brand.getBigDecimal() != null ) {
            carBrand.setPrice( createDecimalFormat( "0.00" ).format( brand.getBigDecimal() ) );
        }

        return carBrand;
    }

    private DecimalFormat createDecimalFormat( String numberFormat ) {

        DecimalFormat df = new DecimalFormat( numberFormat );
        df.setParseBigDecimal( true );
        return df;
    }
}
