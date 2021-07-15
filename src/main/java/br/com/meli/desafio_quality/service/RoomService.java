package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.model.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    public Double getRoomM2 (Room room) {
        return room.getLength() * room.getWidth();
    }
}
