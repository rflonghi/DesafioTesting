package br.com.meli.desafio_quality.dto;

import br.com.meli.desafio_quality.model.District;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class DistrictDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @Size(min = 1, max = 13)
    private BigDecimal value;

    public DistrictDTO() {
    }

    public DistrictDTO(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public District toModel() {
        District district = new District();
        district.setName(getName());
        district.setValue(getValue());

        return district;
    }

}
