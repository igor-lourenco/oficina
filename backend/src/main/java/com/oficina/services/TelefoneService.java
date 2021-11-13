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

import com.oficina.dto.TelefoneDTO;
import com.oficina.entities.Telefone;
import com.oficina.repositories.TelefoneRepository;
import com.oficina.services.exceptions.DatabaseException;
import com.oficina.services.exceptions.ResourceNotFoundException;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<TelefoneDTO> findAll(Pageable pageable) {
		Page<Telefone> entity = repository.findAll(pageable);
		return entity.map(x -> new TelefoneDTO(x)); 
	}
	
	@Transactional(readOnly = true)
	public TelefoneDTO findById(Integer id) {
		Optional<Telefone> entity = repository.findById(id);
		Telefone obj = entity.orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado -> " + id));
		return new TelefoneDTO(obj);
	}
	
	@Transactional
	public TelefoneDTO insert(TelefoneDTO dto) {
		Telefone entity = new Telefone();
		entity.setTipo(dto.getTipo());
		entity.setNumero(dto.getNumero());
		entity = repository.save(entity);
		return new TelefoneDTO(entity);
	}
	
	@Transactional
	public TelefoneDTO update(Integer id, TelefoneDTO dto) {
		try {
			Telefone entity = repository.getOne(id);
			entity.setTipo(dto.getTipo());
			entity.setNumero(dto.getNumero());
			entity = repository.save(entity);
			return new TelefoneDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Telefone não encontrado -> " + id);
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
}
