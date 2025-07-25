package org.example.projecttest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.catalina.mapper.Mapper;
import org.example.projecttest.entity.Brand;
import org.example.projecttest.entity.Car;
import org.example.projecttest.entity.CarDto;
import org.example.projecttest.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.annotation.Resource;



@SpringBootTest 
@ExtendWith(SpringExtension.class)
public class mapStructTest {


    @Resource
    private CarRepository carRepository;

    @Test
    public void Test1(){
        
        Car car = new Car();
        car.setMake("坐车");
        car.setNumberOfSeats(10);
        car.setBrandi(new Brand("宝马",10,LocalDateTime.parse("2025-03-09T14:30:00"),BigDecimal.valueOf(33.7)));
        CarDto trans = carRepository.carToCarDtoV2(car);
        System.out.println(trans);

        DateTimeFormatter pFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse("2025-03-09 14:30:00", pFormatter);
        System.out.println(time);//利用dateTimeFormatter自定义日期格式然后再用LocalDateTime的parse方法将字符串转换成对象
        LocalDateTime localDateTime = LocalDateTime.of(2020, 2, 4, 2, 13);
        String timeStr = localDateTime.format(pFormatter);
        System.out.println(timeStr);//将系统日期转换成字符串 直接利用要转换的日期调用formatter放入格式即可
    }
}
