package com.oficina.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.oficina.entities.Cliente;
import com.oficina.entities.Telefone;
import com.oficina.entities.enums.SexoEnum;

public class ClienteDTO extends RepresentationModel<ClienteDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private SexoEnum sexo;
	
	private List<FindByIdTelefoneDTO> telefones = new ArrayList<>();
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Integer id, String nome, SexoEnum sexo) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
	}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.sexo = obj.getSexo();
	}
	
	public ClienteDTO(Cliente obj, List<Telefone> telefones) {
		this(obj);
		telefones.forEach(tel -> this.telefones.add(new FindByIdTelefoneDTO(tel)));
		
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

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public List<FindByIdTelefoneDTO> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<FindByIdTelefoneDTO> telefones) {
		this.telefones = telefones;
	}
}
