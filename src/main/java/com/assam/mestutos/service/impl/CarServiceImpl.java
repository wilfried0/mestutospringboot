package com.assam.mestutos.service.impl;

import com.assam.mestutos.domain.Car;
import com.assam.mestutos.exception.MesTutosException;
import com.assam.mestutos.repository.CarRepository;
import com.assam.mestutos.service.CarService;
import com.assam.mestutos.service.dto.CarDTO;
import com.assam.mestutos.service.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarDTO createCar(CarDTO carDTO) throws MesTutosException {
        Car car = carMapper.toEntity(carDTO);
        Car c = carRepository.save(car);
        return carMapper.toDTO(c);
    }

    @Override
    public Page<CarDTO> getAllCar(Pageable pageable) {
        Page<Car> cars = carRepository.findAll(pageable);
        return cars.map(carMapper::toDTO);
    }

    @Override
    public List<CarDTO> getAllCarByCategory(Long id) throws MesTutosException {
        List<Car> cars = carRepository.findByCategoryId(id);
        return cars.stream().map(carMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarById(Long id) throws MesTutosException {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new MesTutosException("Couldn't found car with id = "+id)
        );
        return carMapper.toDTO(car);
    }

    @Override
    public void deleteCar(Long id) throws Exception {
        if(carRepository.findById(id).isPresent())
            carRepository.deleteById(id);
        else throw new MesTutosException("Couldn't found car with id = " + id);
    }

    @Override
    public CarDTO updateCar(CarDTO carDTO) throws Exception {
        carRepository.findById(carDTO.getId()).orElseThrow(
                () -> new Exception("Couldn't found car with id = ")
        );
        Car c = carRepository.save(carMapper.toEntity(carDTO));
        return carMapper.toDTO(c);
    }
}
