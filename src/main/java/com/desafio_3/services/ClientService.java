package com.desafio_3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio_3.dto.ClientDTO;
import com.desafio_3.entities.Client;
import com.desafio_3.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).get();
		return new 
				ClientDTO(
						client.getId(), 
						client.getName(), 
						client.getCpf(), 
						client.getIncome(), 
						client.getBirthDate(), 
						client.getChildren());
	}
}
