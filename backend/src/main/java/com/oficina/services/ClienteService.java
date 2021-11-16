package com.oficina.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oficina.dto.ClienteDTO;
import com.oficina.dto.FindByIdTelefoneDTO;
import com.oficina.entities.Cliente;
import com.oficina.entities.Telefone;
import com.oficina.repositories.ClienteRepository;
import com.oficina.repositories.TelefoneRepository;
import com.oficina.services.exceptions.DatabaseException;
import com.oficina.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private TelefoneRepository telRepository;
	
	
	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAll(Pageable pageable) {
		Page<Cliente> entity = repository.findAll(pageable);
		return entity.map(x -> new ClienteDTO(x, x.getTelefones())); 
	}
	
	@Transactional(readOnly = true)
	public ClienteDTO findById(Integer id) {
		Optional<Cliente> entity = repository.findById(id);
		Cliente obj = entity.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado -> " + id));
		return new ClienteDTO(obj, obj.getTelefones());
	}
	
	@Transactional
	public ClienteDTO insert(ClienteDTO dto) {
		Cliente entity = new Cliente();
		copyToEntity(entity, dto);
		entity = repository.save(entity);
		return new ClienteDTO(entity);
	}
	

	@Transactional
	public ClienteDTO update(Integer id, ClienteDTO dto) {
		try {
			Cliente entity = repository.getOne(id);
			copyToEntity(entity, dto);
			entity = repository.save(entity);
			return new ClienteDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cliente não encontrado -> " + id);
		}
	}
	
	public void delete(Integer id) {
		try {
			repository.deleteById(id);			
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado -> " + id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade no banco");
		}
	}
	
	private void copyToEntity(Cliente entity, ClienteDTO dto) {
		entity.setNome(dto.getNome());
		entity.setSexo(dto.getSexo());
		
		entity.getTelefones().clear();
		for(FindByIdTelefoneDTO telDTO : dto.getTelefones()) {
			Telefone tel = telRepository.getOne(telDTO.getId());
			entity.getTelefones().add(tel);
		}
	}
}
