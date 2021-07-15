package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.dto.HouseDTO;
import br.com.meli.desafio_quality.model.House;
import br.com.meli.desafio_quality.model.Room;
import br.com.meli.desafio_quality.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;

@Service
public class HouseService {

  @Autowired
  RoomService roomService;

  @Autowired
  HouseRepository houseRepository;

  public HouseDTO createHouse(HouseDTO houseDTO) {
    House house = houseRepository.save(houseDTO.toModel());
    houseDTO.setId(house.getId());

    return houseDTO;
  }

  public double getTotalArea(House house) {
    return house.getRooms().stream().mapToDouble(r -> roomService.getRoomArea(r)).sum();
  }

  public BigDecimal calculateHousePrice (House house){
    return house.getDistrict().getValue().multiply(BigDecimal.valueOf(getTotalArea(house)));
  }

  public Room getBiggestRoom(House house) {
    house.getRooms().sort(Comparator.comparingDouble(r -> roomService.getRoomArea(r)));
    Collections.reverse(house.getRooms());

    return house.getRooms().get(0);
  }
}
