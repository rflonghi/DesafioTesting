package br.com.meli.desafio_quality.repository;

import br.com.meli.desafio_quality.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    Optional<District> findByName(String name);

}
