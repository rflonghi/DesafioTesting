package br.com.meli.desafio_quality.dto;

import br.com.meli.desafio_quality.model.Room;

import javax.validation.constraints.*;

public class RoomDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    @Pattern(regexp = "\\b[A-Z][\\w\\s]*\\b", message = "A primeira letra deve ser maiúscula")
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
