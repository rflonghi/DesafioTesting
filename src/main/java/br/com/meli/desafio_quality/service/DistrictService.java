package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.dto.DistrictDTO;
import br.com.meli.desafio_quality.exception.DistrictNotFoundException;
import br.com.meli.desafio_quality.model.District;
import br.com.meli.desafio_quality.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
// Author: Arthur Donizetti
*/
@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    /*
    //  Create new district based on districtDTO
    */
    public DistrictDTO createDistrict(DistrictDTO districtDTO) {
        District district = districtRepository.save(districtDTO.toModel());
        districtDTO.setId(district.getId());
        return districtDTO;
    }

    /*
    //  Find district by name
    */
    public District getDistrictByName(String name) {
        Optional<District> district = districtRepository.findByName(name);
        if (district.isEmpty())
            throw new DistrictNotFoundException(name);
        return district.get();
    }

}
