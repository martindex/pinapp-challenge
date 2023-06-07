package com.pinapp.challenge.ar.services;

import java.util.List;
import com.pinapp.challenge.ar.dtos.ClientDto;
import com.pinapp.challenge.ar.dtos.ClientStatsDto;

public interface ClientService {
    void createClient(ClientDto clientDto);

    ClientStatsDto getClientStats();

    List<ClientDto> getClientListWithEstimatedDeathDate();
}
