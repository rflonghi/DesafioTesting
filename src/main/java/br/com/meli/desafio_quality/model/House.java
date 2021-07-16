package br.com.meli.desafio_quality.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tbl_houses")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    private String districtName;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<Room> rooms;

    public House() {
    }

    public House(String name, String districtName, List<Room> rooms) {
        this.name = name;
        this.districtName = districtName;
        this.rooms = rooms;
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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
