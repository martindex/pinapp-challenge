package com.pinapp.challenge.ar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pinapp.challenge.ar.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

