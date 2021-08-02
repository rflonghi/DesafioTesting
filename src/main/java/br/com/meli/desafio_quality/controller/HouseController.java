package br.com.meli.desafio_quality.controller;

import br.com.meli.desafio_quality.dto.DistrictDTO;
import br.com.meli.desafio_quality.dto.HouseDTO;
import br.com.meli.desafio_quality.dto.HouseRoomSizeDTO;
import br.com.meli.desafio_quality.model.Room;
import br.com.meli.desafio_quality.service.DistrictService;
import br.com.meli.desafio_quality.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/*
// Author: Michel Andrew
*/
@RestController
@RequestMapping(value = "/api/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @Autowired
    DistrictService districtService;

    @PostMapping("/create")
    public ResponseEntity<HouseDTO> createHouse(@Valid @RequestBody HouseDTO houseDTO) {
        districtService.getDistrictByName(houseDTO.getDistrictName());
        return ResponseEntity.ok(houseService.createHouse(houseDTO));
    }

    @GetMapping(value = "/{id}/totalArea")
    public ResponseEntity<Map<String, Double>> getTotalArea(@PathVariable(value = "id") Long id) {
        Map<String, Double> result = new HashMap<>();
        result.put("Area", houseService.getTotalAreaById(id));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}/totalPrice")
    public ResponseEntity<Map<String, Double>> getTotalPrice(@PathVariable(value = "id") Long id) {
        Map<String, Double> result = new HashMap<>();
        result.put("Price", houseService.calculateHousePriceById(id).doubleValue());
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}/biggestRoom")
    public ResponseEntity<Map<String, Room>> getBiggestRoom(@PathVariable(value = "id") Long id) {
        Map<String, Room> result = new HashMap<>();
        result.put("BiggestRoom", houseService.getBiggestRoomByHouseId(id));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}/rooms")
    public ResponseEntity<HouseRoomSizeDTO> getRoomsSizes(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(houseService.getRoomsByHouseId(id));
    }
}
