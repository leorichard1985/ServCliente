package com.fiap.servCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.servCliente.model.jpaStructure.ClienteJpa;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteJpa, Integer> {
	

}
