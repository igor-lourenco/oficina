package com.oficina.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.dto.RootEntryPointDTO;

@RestController
public class RootEntryPointResource {

	@GetMapping
	public ResponseEntity<RootEntryPointDTO> root(){
		RootEntryPointDTO obj = new RootEntryPointDTO();
		
		obj.add(linkTo(methodOn(TelefoneResource.class).findAll(null)).withRel("Telefone"));
		obj.add(linkTo(methodOn(ClienteResource.class).findAll(null)).withRel("Cliente"));
		
		return ResponseEntity.ok().body(obj);
	}
}
