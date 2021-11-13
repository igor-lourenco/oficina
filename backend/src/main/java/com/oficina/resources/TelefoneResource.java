package com.oficina.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.dto.TelefoneDTO;
import com.oficina.services.TelefoneService;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneResource {

	@Autowired
	private TelefoneService service;
	
	@GetMapping
	public ResponseEntity<List<TelefoneDTO>> findAll(){
		
		List<TelefoneDTO> dto = service.findAll();
		return ResponseEntity.ok().body(dto);
	}
}
