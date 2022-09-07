package br.com.elo7.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.elo7.desafio.entities.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

}
