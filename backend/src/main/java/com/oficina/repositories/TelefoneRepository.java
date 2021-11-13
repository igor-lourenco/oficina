package com.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oficina.entities.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {

}
