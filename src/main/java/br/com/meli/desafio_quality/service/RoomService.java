package br.com.meli.desafio_quality.service;

import br.com.meli.desafio_quality.model.Room;
import org.springframework.stereotype.Service;

/*
// Author: Rodrigo Longhi
*/
@Service
public class RoomService {

    /*
    //  Obtain room area
    */
    public Double getRoomArea(Room room) {
        return room.getLength() * room.getWidth();
    }
}
