package com.pinapp.challenge.ar.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pinapp.challenge.ar.dtos.ClientDto;
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

    @Override
    public ClientDto getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    public List<ClientDto> getClientList() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }
}

