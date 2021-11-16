package com.oficina.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.oficina.entities.Telefone;

public class FindByIdTelefoneDTO extends RepresentationModel<FindByIdTelefoneDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numero;
	
	public FindByIdTelefoneDTO() {
		
	}

	public FindByIdTelefoneDTO(Integer id, String numero) {
		this.id = id;
		this.numero = numero;
	}
	
	public FindByIdTelefoneDTO(Telefone obj) {
		this.id = obj.getId();
		this.numero = obj.getNumero();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
