package com.assam.mestutos.service;

import com.assam.mestutos.domain.Car;
import com.assam.mestutos.domain.Category;
import com.assam.mestutos.domain.enumeration.ModelEnum;
import com.assam.mestutos.exception.MesTutosException;
import com.assam.mestutos.repository.CarRepository;
import com.assam.mestutos.service.dto.CarDTO;
import com.assam.mestutos.service.dto.CategoryDTO;
import com.assam.mestutos.service.impl.CarServiceImpl;
import com.assam.mestutos.service.mapper.CarMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarMapper carMapper;
    @InjectMocks
    private CarServiceImpl carServiceImpl;

    @Test
    void createCar() throws MesTutosException {
        Mockito.when(carRepository.save(ArgumentMatchers.any())).thenAnswer(
                (invocationOnMock) -> {
                    Car car = (Car) invocationOnMock.getArgument(0);
                    car.setId(1L);
                    return car;
                }
        );

        Mockito.when(carMapper.toEntity(ArgumentMatchers.any(CarDTO.class))).thenAnswer(
                (invocationOnMock) -> getCar(invocationOnMock)
        );

        Mockito.when(carMapper.toDTO(ArgumentMatchers.any(Car.class))).thenAnswer(
                (invocationOnMock) -> getCarDTO(invocationOnMock)
        );

        CarDTO actualCarDTO = CarDTO.builder()
                .name("Audi")
                .model(ModelEnum.QUATRE_ROUES)
                .description("")
                .categoryDTO(new CategoryDTO(1L, "Sans remorque"))
                .build();
        CarDTO expectedCarDTO = carServiceImpl.createCar(actualCarDTO);

        Assertions.assertAll(
                () -> assertThat(expectedCarDTO).isNotNull(),
                () -> assertThat(expectedCarDTO.getId()).isNotNull(),
                () -> assertThat(actualCarDTO.getName()).isEqualTo(expectedCarDTO.getName()),
                () -> assertThat(actualCarDTO.getDescription()).isEqualTo(expectedCarDTO.getDescription()),
                () -> assertThat(actualCarDTO.getModel()).isEqualTo(expectedCarDTO.getModel()),
                () -> assertThat(actualCarDTO.getCategoryDTO()).isEqualTo(expectedCarDTO.getCategoryDTO())
        );

        Mockito.verify(carRepository).save(ArgumentMatchers.any());
        Mockito.verify(carMapper).toEntity(ArgumentMatchers.any(CarDTO.class));
        Mockito.verify(carMapper).toDTO(ArgumentMatchers.any(Car.class));
    }

    private static CarDTO getCarDTO(InvocationOnMock invocationOnMock) {
        Car car = (Car) invocationOnMock.getArgument(0);
        CategoryDTO categoryDTO = new CategoryDTO(car.getCategory().getId(), car.getCategory().getName());
        return new CarDTO(car.getId(), car.getName(), car.getDescription(), categoryDTO, car.getModel());
    }
    private static Car getCar(InvocationOnMock invocationOnMock) {
        CarDTO carDTO = (CarDTO) invocationOnMock.getArgument(0);
        Category category = new Category(carDTO.getCategoryDTO().getId(), carDTO.getCategoryDTO().getName());
        return new Car(carDTO.getId(), carDTO.getName(), carDTO.getDescription(), category, carDTO.getModel());
    }
}
