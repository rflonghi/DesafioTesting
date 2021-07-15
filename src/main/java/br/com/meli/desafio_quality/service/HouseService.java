package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HouseService {

  @Autowired
  RoomService roomService;

  public double getTotalArea(House house) {
    return house.getRooms().stream().mapToDouble(r -> roomService.getRoomArea(r)).sum();
  }

  public BigDecimal calculateHousePrice (House house){
    return house.getDistrict().getValue().multiply(BigDecimal.valueOf(getTotalArea(house)));
  }
}
