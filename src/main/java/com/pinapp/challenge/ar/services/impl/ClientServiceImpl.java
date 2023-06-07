package com.pinapp.challenge.ar.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pinapp.challenge.ar.dtos.ClientDto;
import com.pinapp.challenge.ar.dtos.ClientStatsDto;
import com.pinapp.challenge.ar.entities.Client;
import com.pinapp.challenge.ar.repositories.ClientRepository;
import com.pinapp.challenge.ar.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        clientRepository.save(client);
    }

    public double getAverageAge(List<Client> clients){
        return clients.stream()
                .mapToInt(Client::getAge)
                .average()
                .orElse(0);
    }

    public double getStandardDeviation(List<Client> clients, double averageAge){
        return Math.sqrt(
                clients.stream()
                        .mapToDouble(Client::getAge)
                        .map(age -> Math.pow(age - averageAge, 2))
                        .average()
                        .orElse(0)
        );
    }

    @Override
    public ClientStatsDto getClientStats() {
        List<Client> clients = clientRepository.findAll();
        double averageAge = getAverageAge(clients);
        double standardDeviation = getStandardDeviation(clients, averageAge);
        return new ClientStatsDto(averageAge, standardDeviation);
    }

    @Override
    public List<ClientDto> getClientListWithEstimatedDeathDate() {
        List<Client> clients = clientRepository.findAll();
        double averageAge = getAverageAge(clients);
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .peek(client -> {
                    int estimatedDeathYear = (int)averageAge + client.getDateOfBirth().getYear();
                    LocalDate estimatedDeathDate = client.getDateOfBirth().withYear(estimatedDeathYear);
                    client.setEstimatedDeathDate(estimatedDeathDate);
                })
                .collect(Collectors.toList());
    }
}

