package com.desafio_3.dto;

import java.time.LocalDate;

public record ClientDTO (
		Long id,
		String name,
		String cpf,
		Double income,
		LocalDate birthDate,
		Integer children
		){

}
