package com.desafio_3.mappers;

import org.mapstruct.factory.Mappers;

import com.desafio_3.dto.ClientDTO;
import com.desafio_3.entities.Client;

public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
	
	ClientDTO toDTO(Client client);
	Client toEntity(ClientDTO clientDTO);
}
