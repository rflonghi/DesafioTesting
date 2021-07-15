package br.com.meli.desafio_quality.dto;

import br.com.meli.desafio_quality.model.District;
import br.com.meli.desafio_quality.model.House;
import br.com.meli.desafio_quality.model.Room;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class HouseDTO {
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    @Pattern(regexp = "\\b[A-Z][\\w\\s]*\\b", message = "A primeira letra deve ser maiúscula")
    private String name;

    @NotNull
    @Valid
    private DistrictDTO district;

    @NotNull
    @Valid
    @Size(min=1, max=500)
    private List<RoomDTO> rooms;

    public HouseDTO() {
    }

    public HouseDTO(String name, DistrictDTO district, List<RoomDTO> rooms) {
        this.name = name;
        this.district = district;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public House toModel() {
        House house = new House();
        house.setName(getName());
        house.setDistrict(getDistrict().toModel());
        house.setRooms(getRooms().stream().map(RoomDTO::toModel).collect(Collectors.toList()));

        return house;
    }

}
