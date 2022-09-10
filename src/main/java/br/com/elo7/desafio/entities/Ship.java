package br.com.elo7.desafio.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.elo7.desafio.dto.request.ShipRequest;

@Entity
@Table(name = "TB_SHIP")
public class Ship implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	public Ship() {

	}

	public Ship(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Ship(ShipRequest shipRequest) {
		this.name = shipRequest.getName();
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

}
