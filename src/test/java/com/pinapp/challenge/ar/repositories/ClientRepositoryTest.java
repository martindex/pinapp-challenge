package com.pinapp.challenge.ar.repositories;

import com.pinapp.challenge.ar.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testSaveClient() {
        // Arrange
        Client client = new Client("John", "Doe", 30, LocalDate.of(1990, 1, 1));

        // Act
        Client savedClient = clientRepository.save(client);
        entityManager.flush();
        entityManager.clear();

        // Assert
        Optional<Client> retrievedClientOptional = clientRepository.findById(savedClient.getId());
        Assertions.assertTrue(retrievedClientOptional.isPresent());
        Client retrievedClient = retrievedClientOptional.get();
        Assertions.assertEquals("John", retrievedClient.getFirstName());
        Assertions.assertEquals("Doe", retrievedClient.getLastName());
        Assertions.assertEquals(30, retrievedClient.getAge());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), retrievedClient.getDateOfBirth());
    }

    @Test
    public void testFindAllClients() {
        // Arrange
        Client client1 = new Client("John", "Doe", 30, LocalDate.of(1990, 1, 1));
        Client client2 = new Client("Jane", "Smith", 25, LocalDate.of(1995, 5, 10));
        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.flush();
        entityManager.clear();

        // Act
        List<Client> clients = clientRepository.findAll();

        // Assert
        Assertions.assertEquals(2, clients.size());
        Assertions.assertTrue(clients.contains(client1));
        Assertions.assertTrue(clients.contains(client2));
    }

}
