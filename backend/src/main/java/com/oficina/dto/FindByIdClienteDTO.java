package com.oficina.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.oficina.entities.Cliente;

public class FindByIdClienteDTO extends RepresentationModel<FindByIdClienteDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public FindByIdClienteDTO() {

	}

	public FindByIdClienteDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public FindByIdClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
