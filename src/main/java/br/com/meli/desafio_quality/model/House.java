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
    @OneToOne(cascade = CascadeType.ALL)
    private District district;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<Room> rooms;

    public House() {
    }

    public House(String name, District district, List<Room> rooms) {
        this.name = name;
        this.district = district;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
