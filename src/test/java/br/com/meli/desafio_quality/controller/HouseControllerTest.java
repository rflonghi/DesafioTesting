package br.com.meli.desafio_quality.controller;

import br.com.meli.desafio_quality.model.District;
import br.com.meli.desafio_quality.model.House;
import br.com.meli.desafio_quality.model.Room;
import br.com.meli.desafio_quality.repository.HouseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
