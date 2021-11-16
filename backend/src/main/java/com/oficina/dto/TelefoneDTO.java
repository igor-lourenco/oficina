package com.oficina.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.oficina.entities.Telefone;
import com.oficina.entities.enums.TipoEnum;

public class TelefoneDTO extends RepresentationModel<TelefoneDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numero;
	private TipoEnum tipo;
	
	private FindByIdClienteDTO cliente;
	
	public TelefoneDTO() {
		
	}

	public TelefoneDTO(Integer id, String numero, TipoEnum tipo, FindByIdClienteDTO cliente) {
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.cliente = cliente;
	}
	
	public TelefoneDTO(Telefone obj) {
		this.id = obj.getId();
		this.numero = obj.getNumero();
		this.tipo = obj.getTipo();
		this.cliente = new FindByIdClienteDTO(obj.getCliente());
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

	public FindByIdClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(FindByIdClienteDTO cliente) {
		this.cliente = cliente;
	}
}
