package com.desafio_3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.desafio_3.dto.ClientDTO;
import com.desafio_3.entities.Client;
import com.desafio_3.mappers.ClientMapper;
import com.desafio_3.repositories.ClientRepository;
import com.desafio_3.services.exceptions.DatabaseException;
import com.desafio_3.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return ClientMapper.INSTANCE.toDTO(client);
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		    Page<Client> clients = repository.findAll(pageable);
		    return ClientMapper.INSTANCE.toDTOPage(clients);
		}
	
	@Transactional
	public ClientDTO insert (ClientDTO dto) {
		Client client = ClientMapper.INSTANCE.toEntity(dto); // Converte o DTO para a entidade Client
		Client savedClient = repository.save(client);// Salva a entidade no banco de dados
		return ClientMapper.INSTANCE.toDTO(savedClient);// Converte a entidade salva de volta para DTO
		
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = repository.getReferenceById(id);
			ClientMapper.INSTANCE.updateFromDTO(dto, client);
			Client updatedClient = repository.save(client);
			return ClientMapper.INSTANCE.toDTO(updatedClient);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso Não encontrado");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
		
	}
	
}
