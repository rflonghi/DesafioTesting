package br.com.meli.desafio_quality.dto;

import br.com.meli.desafio_quality.model.District;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class DistrictDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    private BigDecimal value;

    public DistrictDTO() {
    }

    public DistrictDTO(@NotNull @Size(min = 3, max = 45) String name, @NotNull BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
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
