package br.com.meli.desafio_quality.dto;

import br.com.meli.desafio_quality.model.Room;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoomDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    @Min(value = 1)
    @Max(value = 33)
    private Double length;

    @NotNull
    @Min(value = 1)
    @Max(value = 25)
    private Double width;

    public RoomDTO() {
    }

    public RoomDTO(String name, Double length, Double width) {
        this.name = name;
        this.length = length;
        this.width = width;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Room toModel() {
        Room room = new Room();
        room.setName(getName());
        room.setLength(getLength());
        room.setWidth(getWidth());

        return room;
    }

}
