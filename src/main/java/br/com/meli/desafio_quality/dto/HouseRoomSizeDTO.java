package br.com.meli.desafio_quality.dto;

import java.util.Map;

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
}
