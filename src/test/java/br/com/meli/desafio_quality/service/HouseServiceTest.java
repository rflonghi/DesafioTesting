package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.model.District;
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

@SpringBootTest
public class HouseServiceTest {

  @Autowired
  HouseService houseService;

  @Test
  public void getHouseTotalAreaTest_mustPass() {
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    District district = new District("Pirituba City", new BigDecimal(2000));

    House house = new House("Patrícia", district, rooms);

    assertEquals(23, houseService.getTotalArea(house));
  }

  @Test
  public void getHouseTotalAreaTest_mustNotPass() {
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    District district = new District("Pirituba City", new BigDecimal(2000));

    House house = new House("Patrícia", district, rooms);

    assertNotEquals(27, houseService.getTotalArea(house));
  }

  @Test
  public void getHousePrice_mustPass(){
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    District district = new District("Pirituba City", new BigDecimal(2000));

    House house = new House("Patrícia", district, rooms);

    assertEquals(new BigDecimal("46000.0"),houseService.calculateHousePrice(house));
  }

  @Test
  public void getHousePrice_mustNotPass(){
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room("Bedroom", 2d, 3d));
    rooms.add(new Room("Kitchen", 4d, 2d));
    rooms.add(new Room("Living Room", 3d, 3d));

    District district = new District("Pirituba City", new BigDecimal(2000));

    House house = new House("Patrícia", district, rooms);

    assertNotEquals(new BigDecimal("49000.0"),houseService.calculateHousePrice(house));
  }
}
