package br.com.elo7.desafio.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlanetRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "The name can't be null or blank")
	@Size(min = 1, max = 20, message = "The name must be between 1 and 20 characters")
	private String name;

	@NotNull
	@Min(value = 0, message = "The planet width can't be negative")
	private Integer width;

	@NotNull
	@Min(value = 0, message = "The planet height can't be negative")
	private Integer height;

	public PlanetRequest() {

	}

	public PlanetRequest(String name, Integer width, Integer height) {
		this.name = name;
		this.width = width;
		this.height = height;
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