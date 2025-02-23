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
	
	@Transactional
	public ClientDTO insert (ClientDTO dto) {
		Client client = ClientMapper.INSTANCE.toEntity(dto); // Converte o DTO para a entidade Client
		Client savedClient = repository.save(client);// Salva a entidade no banco de dados
		return ClientMapper.INSTANCE.toDTO(savedClient);// Converte a entidade salva de volta para DTO
		
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		Client client = repository.getReferenceById(id);
		ClientMapper.INSTANCE.updateFromDTO(dto, client);
		Client updatedClient = repository.save(client);
		return ClientMapper.INSTANCE.toDTO(updatedClient);
	}
	
	@Transactional
	public void delete(long id) {
		repository.deleteById(id);
	}
	
}
