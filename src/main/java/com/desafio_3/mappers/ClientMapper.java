package com.desafio_3.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.desafio_3.dto.ClientDTO;
import com.desafio_3.entities.Client;

@Mapper
public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

	ClientDTO toDTO(Client client);

	Client toEntity(ClientDTO clientDTO);

	List<ClientDTO> toDTOList(List<Client> clients);
	
	default Page<ClientDTO> toDTOPage(Page<Client> clientPage) {
        List<ClientDTO> clientDTOs = toDTOList(clientPage.getContent());
        return new PageImpl<>(clientDTOs, clientPage.getPageable(), clientPage.getTotalElements());
      //Mapper não consegue mapear tipos como o page, apenas tipo List. Por isso a conversão
	}
	
	// Método adicional para atualizar a entidade com os dados do DTO
    @Mapping(target = "id", ignore = true) // Ignora o id (não queremos sobrescrevê-lo)
    void updateFromDTO(ClientDTO clientDTO, @MappingTarget Client client);
	
}
