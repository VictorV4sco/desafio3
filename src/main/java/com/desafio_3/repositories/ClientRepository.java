package com.desafio_3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio_3.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
