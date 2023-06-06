package com.pinapp.challenge.ar.services;

import java.util.List;
import com.pinapp.challenge.ar.dtos.ClientDto;

public interface ClientService {
    void createClient(ClientDto clientDto);
    ClientDto getClient(Long id);
    List<ClientDto> getClientList();
}
