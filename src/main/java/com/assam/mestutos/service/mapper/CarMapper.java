package com.assam.mestutos.service.mapper;

import com.assam.mestutos.domain.Car;
import com.assam.mestutos.service.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CarMapper {

    @Mappings({
            @Mapping(source = "categoryDTO", target = "category")
    })
    Car toEntity(CarDTO carDTO);
    List<CarDTO> toEntity(List<CarDTO> carDTO);
    @Mappings({
            @Mapping(source = "category", target = "categoryDTO")
    })
    CarDTO toDTO(Car car);
    List<CarDTO> toDTO(List<Car> car);
}
