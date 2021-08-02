package br.com.meli.desafio_quality.controller;

import br.com.meli.desafio_quality.dto.DistrictDTO;
import br.com.meli.desafio_quality.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
// Author: Arthur Donizetti
*/
@RestController
@RequestMapping(value = "/api/district")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @PostMapping("/create")
    public ResponseEntity<DistrictDTO> createDistrict(@Valid @RequestBody DistrictDTO districtDTO) {
        return ResponseEntity.ok(districtService.createDistrict(districtDTO));
    }

}
