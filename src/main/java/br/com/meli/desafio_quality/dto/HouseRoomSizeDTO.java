package br.com.meli.desafio_quality.dto;

import java.util.Map;
import java.util.Objects;

/*
// Author: Michel Andrew
*/
public class HouseRoomSizeDTO {

    private String houseName;

    private String houseDistrict;

    private Map<String, Double> rooms;

    public HouseRoomSizeDTO() {
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseDistrict() {
        return houseDistrict;
    }

    public void setHouseDistrict(String houseDistrict) {
        this.houseDistrict = houseDistrict;
    }

    public Map<String, Double> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, Double> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseRoomSizeDTO that = (HouseRoomSizeDTO) o;
        return houseName.equals(that.houseName)  && houseDistrict.equals(that.houseDistrict)  && Objects.equals(rooms, that.rooms);
    }
}
