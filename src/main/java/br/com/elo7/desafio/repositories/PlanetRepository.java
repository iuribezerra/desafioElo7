package br.com.elo7.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elo7.desafio.entities.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

}
