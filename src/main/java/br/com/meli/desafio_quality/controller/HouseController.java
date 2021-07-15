package br.com.meli.desafio_quality.controller;

import br.com.meli.desafio_quality.dto.HouseDTO;
import br.com.meli.desafio_quality.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @PostMapping("/create")
    public ResponseEntity<HouseDTO> createHouse(@Valid @RequestBody HouseDTO houseDTO) {
        return ResponseEntity.ok(houseService.createHouse(houseDTO));
    }
}
