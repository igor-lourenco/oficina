package com.oficina.dto;

import java.io.Serializable;

import com.oficina.entities.Telefone;
import com.oficina.entities.enums.TipoEnum;

public class TelefoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numero;
	private TipoEnum tipo;
	
	public TelefoneDTO() {
		
	}

	public TelefoneDTO(Integer id, String numero, TipoEnum tipo) {
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
	}
	
	public TelefoneDTO(Telefone obj) {
		this.id = obj.getId();
		this.numero = obj.getNumero();
		this.tipo = obj.getTipo();
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

	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}
}
