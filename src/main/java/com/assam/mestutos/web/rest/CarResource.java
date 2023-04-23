package com.assam.mestutos.web.rest;

import com.assam.mestutos.exception.MesTutosException;
import com.assam.mestutos.service.CarService;
import com.assam.mestutos.service.dto.CarDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarResource {
    private final CarService carService;

    @Operation(summary = "créer un nouveau car", description = "cette méthode attends une CarDTO et retourne une CarDTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarDTO carDTO) {
        try{
            CarDTO c = carService.createCar(carDTO);
            URI location = URI.create(String.format("/api/car/%s", c.getId().toString()));
            return ResponseEntity.created(location).body(c);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<Page<CarDTO>> getAllCar(Pageable pageable){
        Page<CarDTO> cars = carService.getAllCar(pageable);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCarById(@PathVariable Long id) {
        try {
            CarDTO carDTO = carService.getCarById(id);
            return ResponseEntity.ok(carDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable Long id) throws Exception {
        carService.deleteCar(id);
    }

    @PutMapping("/update")
    public ResponseEntity<CarDTO> updateCar(@Valid @RequestBody CarDTO carDTO) throws Exception {
        CarDTO c = carService.updateCar(carDTO);
        return ResponseEntity.ok(c);
    }

    @Operation(summary = "Récupérer la liste des voitures d'une catégorie donnée", description = "cette api rest atends l'id de la ctégorie, et retourne la liste des voiure liées à cette catégorie")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CarDTO.class)))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/category/{id}")
    public ResponseEntity<Object> getAllCarByCategoryId(@PathVariable Long id){
        try {
            List<CarDTO> carDTOList = carService.getAllCarByCategory(id);
            return ResponseEntity.ok(carDTOList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
