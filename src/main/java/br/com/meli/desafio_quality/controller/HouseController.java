package br.com.meli.desafio_quality.controller;

import br.com.meli.desafio_quality.dto.HouseDTO;
import br.com.meli.desafio_quality.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @PostMapping("/create")
    public ResponseEntity<HouseDTO> createHouse(@Valid @RequestBody HouseDTO houseDTO) {
        return ResponseEntity.ok(houseService.createHouse(houseDTO));
    }

    @GetMapping(value = "/{id}/totalArea")
    public ResponseEntity<Map<String, Double>> getTotalArea(@PathVariable(value = "id") Long id) {
        Map<String, Double> result = new HashMap<>();
        result.put("Area", houseService.getTotalAreaById(id));
        return ResponseEntity.ok(result);
    }
}
