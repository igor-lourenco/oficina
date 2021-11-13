package com.oficina.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oficina.dto.TelefoneDTO;
import com.oficina.services.TelefoneService;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneResource {

	@Autowired
	private TelefoneService service;

	@GetMapping
	public ResponseEntity<List<TelefoneDTO>> findAll() {
		List<TelefoneDTO> dto = service.findAll();
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TelefoneDTO> findById(@PathVariable Integer id) {
		TelefoneDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<TelefoneDTO> insert(@RequestBody TelefoneDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto); // mostra no corpo da página a entidade criada
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TelefoneDTO> update(@PathVariable Integer id, @RequestBody TelefoneDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TelefoneDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
