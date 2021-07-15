package br.com.meli.desafio_quality.repository;

import br.com.meli.desafio_quality.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
