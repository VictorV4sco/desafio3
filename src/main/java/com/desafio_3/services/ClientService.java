package com.desafio_3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio_3.dto.ClientDTO;
import com.desafio_3.entities.Client;
import com.desafio_3.mappers.ClientMapper;
import com.desafio_3.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).get();
		return ClientMapper.INSTANCE.toDTO(client);
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		    Page<Client> clients = repository.findAll(pageable);
		    return ClientMapper.INSTANCE.toDTOPage(clients);
		}
	
}
