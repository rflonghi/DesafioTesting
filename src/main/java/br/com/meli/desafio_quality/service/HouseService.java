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

@Service
public class HouseService {

  @Autowired
  RoomService roomService;

  @Autowired
  HouseRepository houseRepository;

  @Autowired
  DistrictService districtService;

  public HouseDTO createHouse(HouseDTO houseDTO) {
    House house = houseRepository.save(houseDTO.toModel());
    houseDTO.setId(house.getId());

    return houseDTO;
  }

  public double getTotalArea(House house) {
    return house.getRooms().stream().mapToDouble(r -> roomService.getRoomArea(r)).sum();
  }

  public BigDecimal calculateHousePrice (House house){
    District district = districtService.getDistrictByName(house.getDistrictName());
    return district.getValue().multiply(BigDecimal.valueOf(getTotalArea(house)));
  }

  public BigDecimal calculateHousePriceById (Long id){
    return calculateHousePrice(findById(id));
  }

  public Room getBiggestRoom(House house) {
    house.getRooms().sort(Comparator.comparingDouble(r -> roomService.getRoomArea(r)));
    Collections.reverse(house.getRooms());

    return house.getRooms().get(0);
  }

  public Room getBiggestRoomByHouseId(Long id) {
    return getBiggestRoom(findById(id));
  }

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

  private House findById(Long id) {
    Optional<House> house = houseRepository.findById(id);
    if (house.isEmpty())
      throw new HouseNotFoundException(id);
    return house.get();
  }
  public double getTotalAreaById(Long id) {
    return getTotalArea(findById(id));
  }
}
