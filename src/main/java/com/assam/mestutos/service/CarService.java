package com.assam.mestutos.service;

import com.assam.mestutos.exception.MesTutosException;
import com.assam.mestutos.service.dto.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarDTO createCar(CarDTO carDTO) throws MesTutosException;
    Page<CarDTO> getAllCar(Pageable pageable);
    List<CarDTO> getAllCarByCategory(Long id) throws MesTutosException;
    CarDTO getCarById(Long id) throws MesTutosException;
    void deleteCar(Long id) throws Exception;
    CarDTO updateCar(CarDTO carDTO) throws Exception;
}