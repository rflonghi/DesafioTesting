package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.dto.DistrictDTO;
import br.com.meli.desafio_quality.model.House;
import br.com.meli.desafio_quality.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
// Author: Rodrigo Longhi
*/
@SpringBootTest
public class HouseServiceTest {

  @Autowired
  HouseService houseService;

  @Autowired
  DistrictService districtService;

  @Test
  public void getHouseTotalAreaTest_mustPass() {
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));


    House house = new House("Patrícia", "Pirituba City", rooms);

    assertEquals(23, houseService.getTotalArea(house));
  }

  @Test
  public void getHouseTotalAreaTest_mustNotPass() {
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    House house = new House("Patrícia", "Pirituba City", rooms);

    assertNotEquals(27, houseService.getTotalArea(house));
  }

  @Test
  public void getHousePrice_mustPass(){
    districtService.createDistrict(new DistrictDTO("Rochdale", new BigDecimal("2000.0")));

    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    House house = new House("Patrícia", "Rochdale", rooms);

    assertEquals(new BigDecimal("46000.0").doubleValue(),houseService.calculateHousePrice(house).doubleValue());
  }

  @Test
  public void getHousePrice_mustNotPass(){
    districtService.createDistrict(new DistrictDTO("Morumbi", new BigDecimal("2000.0")));

    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    House house = new House("Patrícia", "Morumbi", rooms);

    assertNotEquals(new BigDecimal("49000.0"),houseService.calculateHousePrice(house));
  }

  @Test
  public void getBiggestRoom_mustPass(){
    districtService.createDistrict(new DistrictDTO("Jardim Paulistano", new BigDecimal("2000.0")));

    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    House house = new House("Patrícia", "Jardim Paulistano", rooms);

    assertEquals("Living Room",houseService.getBiggestRoom(house).getName());
  }

  @Test
  public void getBiggestRoom_mustNotPass(){
    districtService.createDistrict(new DistrictDTO("Pirituba City", new BigDecimal("2000.0")));

    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    House house = new House("Patrícia", "Pirituba City", rooms);

    assertNotEquals("Bedroom",houseService.getBiggestRoom(house).getName());
  }



}
