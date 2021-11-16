package com.oficina.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.oficina.dto.ClienteDTO;
import com.oficina.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> findAll(Pageable pageable) {
		Page<ClienteDTO> dto = service.findAll(pageable);
		
		//HATEOAS
		for(ClienteDTO obj : dto) {
			int objId = obj.getId();
			obj.add(linkTo(methodOn(ClienteResource.class).findById(objId)).withSelfRel());
		}
		
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		ClienteDTO dto = service.findById(id);
	
		//HATEOAS
		dto.add(linkTo(methodOn(ClienteResource.class).findById(id)).withSelfRel());
		dto.add(linkTo(methodOn(ClienteResource.class).findAll(null)).withRel("findAll"));

		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri(); // mostra no corpo da página a entidade criada
		
		//HATEOAS
		dto.add(linkTo(methodOn(ClienteResource.class).findById(dto.getId())).withSelfRel()); 
		dto.add(linkTo(methodOn(ClienteResource.class).findAll(null)).withRel("findAll"));
		return ResponseEntity.created(uri).body(dto); 
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO dto) {
		dto = service.update(id, dto);
		
		//HATEOAS
		dto.add(linkTo(methodOn(ClienteResource.class).findById(dto.getId())).withSelfRel()); 
		dto.add(linkTo(methodOn(ClienteResource.class).findAll(null)).withRel("findAll"));
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
