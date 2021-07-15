package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Test
    public void getRoomAreaTest_mustPass() {
        Room room = new Room( "Bedroom",5d, 5d);
        assertEquals(25, roomService.getRoomArea(room));
    }

    @Test
    public void getRoomAreaTest_mustNotPass() {
        Room room = new Room( "Bedroom",5d, 5d);
        assertNotEquals(30, roomService.getRoomArea(room));
    }
}
