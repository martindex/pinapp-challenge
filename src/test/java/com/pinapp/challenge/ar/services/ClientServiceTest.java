package com.pinapp.challenge.ar.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.pinapp.challenge.ar.dtos.ClientDto;
import com.pinapp.challenge.ar.dtos.ClientStatsDto;
import com.pinapp.challenge.ar.entities.Client;
import com.pinapp.challenge.ar.repositories.ClientRepository;
import com.pinapp.challenge.ar.services.impl.ClientServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClient() {
        // Arrange
        ClientDto clientDto = new ClientDto("John", "Doe", 30, LocalDate.of(1990, 1, 1));
        Client client = new Client("John", "Doe", 30, LocalDate.of(1990, 1, 1));

        when(modelMapper.map(any(), any())).thenReturn(client);

        // Act
        clientService.createClient(clientDto);

        // Assert
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testGetClientStats() {
        // Arrange
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("John", "Doe", 30, LocalDate.of(1990, 1, 1)));
        clients.add(new Client("Jane", "Smith", 25, LocalDate.of(1995, 1, 1)));

        when(clientRepository.findAll()).thenReturn(clients);

        // Act
        ClientStatsDto statsDto = clientService.getClientStats();

        // Assert
        double expectedAverageAge = 27.5;
        double expectedStandardDeviation = 2.5;

        assertEquals(expectedAverageAge, statsDto.getAverageAge(), 0.001);
        assertEquals(expectedStandardDeviation, statsDto.getStandardDeviation(), 0.001);
    }

    @Test
    public void testGetClientListWithEstimatedDeathDate() {
        //With this clients the average is 27

        // Arrange
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client("John", "Doe", 30, LocalDate.of(1990, 1, 1));
        Client client2 = new Client("Jane", "Smith", 25, LocalDate.of(1995, 1, 1));

        clients.add(client1);
        clients.add(client2);

        ClientDto clientDto1 = new ClientDto("John", "Doe", 30, LocalDate.of(1990, 1, 1));
        ClientDto clientDto2 = new ClientDto("Jane", "Smith", 25, LocalDate.of(1995, 1, 1));

        when(modelMapper.map(client1, ClientDto.class)).thenReturn(clientDto1);
        when(modelMapper.map(client2, ClientDto.class)).thenReturn(clientDto2);

        when(clientRepository.findAll()).thenReturn(clients);

        // Act
        List<ClientDto> clientList = clientService.getClientListWithEstimatedDeathDate();

        // Assert
        assertEquals(2, clientList.size());

        // Assert client 1
        ClientDto clientDtoGet0 = clientList.get(0);
        assertEquals("John", clientDtoGet0.getFirstName());
        assertEquals("Doe", clientDtoGet0.getLastName());
        assertEquals(30, clientDtoGet0.getAge());
        assertEquals(LocalDate.of(1990, 1, 1), clientDtoGet0.getDateOfBirth());
        assertEquals(LocalDate.of(2017, 1, 1), clientDtoGet0.getEstimatedDeathDate());

        // Assert client 2
        ClientDto clientDtoGet1 = clientList.get(1);
        assertEquals("Jane", clientDtoGet1.getFirstName());
        assertEquals("Smith", clientDtoGet1.getLastName());
        assertEquals(25, clientDtoGet1.getAge());
        assertEquals(LocalDate.of(1995, 1, 1), clientDtoGet1.getDateOfBirth());
        assertEquals(LocalDate.of(2022, 1, 1), clientDtoGet1.getEstimatedDeathDate());
    }

}

