package br.com.meli.desafio_quality.controller;

import br.com.meli.desafio_quality.dto.HouseDTO;
import br.com.meli.desafio_quality.dto.HouseRoomSizeDTO;
import br.com.meli.desafio_quality.exception.HouseNotFoundException;
import br.com.meli.desafio_quality.model.District;
import br.com.meli.desafio_quality.model.House;
import br.com.meli.desafio_quality.model.Room;
import br.com.meli.desafio_quality.repository.HouseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class HouseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    HouseRepository houseRepository;

    @Test
    public void createHouse_mustPass() throws Exception {
        District district = new District("Pirituba City", new BigDecimal("2000.0"));
        mockMvc.perform(post("/api/district/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(district)))
                .andExpect(status().isOk());

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Bedroom", 2d, 3d));
        rooms.add(new Room("Kitchen", 4d, 2d));
        rooms.add(new Room("Living Room", 3d, 3d));

        House house = new House("Patricia", "Pirituba City", rooms);

        mockMvc.perform(post("/api/house/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(house)))
                .andExpect(status().isOk());
    }

    @Test
    public void createHouse_mustNotPass() throws Exception {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Bedroom", 2d, 3d));
        rooms.add(new Room("Kitchen", 4d, 2d));
        rooms.add(new Room("Living Room", 3d, 3d));

        House house = new House("patricia", "Pirituba City", rooms);

        mockMvc.perform(post("/api/house/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(house)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createHouse_mustThrowHttpMessageNotReadableException() throws Exception {
        mockMvc.perform(post("/api/house/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createHouse_mustThrowDistrictNotFound() throws Exception {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Bedroom", 2d, 3d));
        rooms.add(new Room("Kitchen", 4d, 2d));
        rooms.add(new Room("Living Room", 3d, 3d));

        House house = new House("Patricia", "Aaa", rooms);

        mockMvc.perform(post("/api/house/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(house)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createHouse_mustThrowHouseNotFound() throws Exception {

        Exception ex = mockMvc.perform(get("/api/house/{id}/totalArea", 9999999L))
                .andExpect(status().isBadRequest()).andReturn().getResolvedException();
        assertEquals(HouseNotFoundException.class, ex.getClass());
    }

    @Test
    public void getRoomSize_mustPass() throws Exception {
        District district = new District("Melicidade", new BigDecimal("2000.0"));
        mockMvc.perform(post("/api/district/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(district)))
                .andExpect(status().isOk());

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Bedroom", 2d, 3d));
        rooms.add(new Room("Kitchen", 4d, 2d));

        House house = new House("Patricia", "Melicidade", rooms);

        MvcResult result = mockMvc.perform(post("/api/house/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(house)))
                .andExpect(status().isOk())
                .andReturn();

        HouseDTO returnedHouse = objectMapper.readValue(result.getResponse().getContentAsString(), HouseDTO.class);


        MvcResult result2 = mockMvc.perform(get("/api/house/{id}/rooms", returnedHouse.getId()))
                .andExpect(status().isOk())
                .andReturn();

        HouseRoomSizeDTO houseRoomSizeDTO = objectMapper.readValue(result2.getResponse().getContentAsString(), HouseRoomSizeDTO.class);

        HouseRoomSizeDTO houseRoomSizeDTO2 = new HouseRoomSizeDTO();
        houseRoomSizeDTO2.setHouseDistrict("Melicidade");
        houseRoomSizeDTO2.setHouseName("Patricia");
        houseRoomSizeDTO2.setRooms(Map.ofEntries(
                entry("Bedroom", 6D ),
                entry("Kitchen", 8D )
        ));

        assertEquals(houseRoomSizeDTO, houseRoomSizeDTO2);
    }

    @Test
    public void getRoomSize_mustNotPass() throws Exception {
        District district = new District("Tangamandapio", new BigDecimal("2000.0"));
        mockMvc.perform(post("/api/district/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(district)))
                .andExpect(status().isOk());

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Bedroom", 2d, 3d));
        rooms.add(new Room("Kitchen", 4d, 2d));

        House house = new House("Patricia", "Tangamandapio", rooms);

        MvcResult result = mockMvc.perform(post("/api/house/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(house)))
                .andExpect(status().isOk())
                .andReturn();

        HouseDTO returnedHouse = objectMapper.readValue(result.getResponse().getContentAsString(), HouseDTO.class);


        MvcResult result2 = mockMvc.perform(get("/api/house/{id}/rooms", returnedHouse.getId()))
                .andExpect(status().isOk())
                .andReturn();

        HouseRoomSizeDTO houseRoomSizeDTO = objectMapper.readValue(result2.getResponse().getContentAsString(), HouseRoomSizeDTO.class);

        HouseRoomSizeDTO houseRoomSizeDTO2 = new HouseRoomSizeDTO();
        houseRoomSizeDTO2.setHouseDistrict("Tangamandapio");
        houseRoomSizeDTO2.setHouseName("Patricia");
        houseRoomSizeDTO2.setRooms(Map.ofEntries(
                entry("Bedroom", 10D ),
                entry("Kitchen", 8D )
        ));
        assertNotEquals(houseRoomSizeDTO, houseRoomSizeDTO2);
    }

//    @Test
//    public void getTotalPrice_mustPass() throws Exception {
//
//    }
}
