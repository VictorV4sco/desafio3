package com.desafio_3.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public record ClientDTO (
		Long id,
		
		@NotBlank(message = "Campo requer preenchimento")
		String name,
		String cpf,
		Double income,
		
		@PastOrPresent(message = "Data inválida")
		LocalDate birthDate,
		Integer children
		){

}
