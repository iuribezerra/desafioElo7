package br.com.elo7.desafio.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ShipRequest implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;

	@NotBlank(message = "The name can't be null or blank")
	@Size(min = 1, max = 20, message = "The name must be between 1 and 20 characters")
	private String name;

	public ShipRequest() {

	}

	public ShipRequest(String name) {
		this.name = name;
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

}
