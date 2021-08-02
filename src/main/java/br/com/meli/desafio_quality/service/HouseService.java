package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.dto.HouseDTO;
import br.com.meli.desafio_quality.dto.HouseRoomSizeDTO;
import br.com.meli.desafio_quality.exception.HouseNotFoundException;
import br.com.meli.desafio_quality.model.District;
import br.com.meli.desafio_quality.model.House;
import br.com.meli.desafio_quality.model.Room;
import br.com.meli.desafio_quality.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

/*
// Author: Michel Andrew
*/
@Service
public class HouseService {

  @Autowired
  RoomService roomService;

  @Autowired
  HouseRepository houseRepository;

  @Autowired
  DistrictService districtService;

  /*
  //  Create new house based on houseDTO
  */
  public HouseDTO createHouse(HouseDTO houseDTO) {
    House house = houseRepository.save(houseDTO.toModel());
    houseDTO.setId(house.getId());

    return houseDTO;
  }

  /*
  //  Obtain area's house
  */
  public double getTotalArea(House house) {
    return house.getRooms().stream().mapToDouble(r -> roomService.getRoomArea(r)).sum();
  }

  /*
  //  Obtain house's price
  */
  public BigDecimal calculateHousePrice (House house){
    District district = districtService.getDistrictByName(house.getDistrictName());
    return district.getValue().multiply(BigDecimal.valueOf(getTotalArea(house)));
  }

  /*
  //  Obtain house's price by id
  */
  public BigDecimal calculateHousePriceById (Long id){
    return calculateHousePrice(findById(id));
  }

  /*
  //  Obtain the biggest room
  */
  public Room getBiggestRoom(House house) {
    house.getRooms().sort(Comparator.comparingDouble(r -> roomService.getRoomArea(r)));
    Collections.reverse(house.getRooms());

    return house.getRooms().get(0);
  }

  /*
  //  Obtain the biggest room by house id
  */
  public Room getBiggestRoomByHouseId(Long id) {
    return getBiggestRoom(findById(id));
  }

  /*
  //  Obtain all rooms by house id
  */
  public HouseRoomSizeDTO getRoomsByHouseId(Long id) {
    House house = findById(id);

    Map<String, Double> rooms = new HashMap<>();

    house.getRooms().forEach(e -> {
      rooms.put(e.getName(), roomService.getRoomArea(e));
    });

    HouseRoomSizeDTO houseRoomSizeDTO = new HouseRoomSizeDTO();
    houseRoomSizeDTO.setHouseName(house.getName());
    houseRoomSizeDTO.setHouseDistrict(house.getDistrictName());
    houseRoomSizeDTO.setRooms(rooms);

    return houseRoomSizeDTO;
  }

  /*
  //  Obtain house by id
  */
  private House findById(Long id) {
    Optional<House> house = houseRepository.findById(id);
    if (house.isEmpty())
      throw new HouseNotFoundException(id);
    return house.get();
  }

  /*
  //  Obtain house total area by house id
  */
  public double getTotalAreaById(Long id) {
    return getTotalArea(findById(id));
  }
}
