package br.com.elo7.desafio.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.elo7.desafio.dto.request.PlanetRequest;

@Entity
@Table(name = "TB_PLANET")
public class Planet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private Integer width;

	private Integer height;
	
	@OneToMany(mappedBy = "planet")
	private List<Mission> mission;

	public Planet() {

	}

	public Planet(Long id, String name, Integer width, Integer height) {
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
	}

	public Planet(PlanetRequest planetRequest) {
		this.id = planetRequest.getId();
		this.name = planetRequest.getName();
		this.width = planetRequest.getWidth();
		this.height = planetRequest.getHeight();
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

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
