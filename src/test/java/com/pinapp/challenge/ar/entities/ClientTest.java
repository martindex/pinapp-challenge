package com.pinapp.challenge.ar.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ClientTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        int age = 30;
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);

        // Act
        Client client = new Client();
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAge(age);
        client.setDateOfBirth(dateOfBirth);

        // Assert
        Assertions.assertEquals(id, client.getId());
        Assertions.assertEquals(firstName, client.getFirstName());
        Assertions.assertEquals(lastName, client.getLastName());
        Assertions.assertEquals(age, client.getAge());
        Assertions.assertEquals(dateOfBirth, client.getDateOfBirth());
    }

    @Test
    public void testConstructor() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        int age = 30;
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);

        // Act
        Client client = new Client(firstName, lastName, age, dateOfBirth);

        // Assert
        Assertions.assertNull(client.getId());
        Assertions.assertEquals(firstName, client.getFirstName());
        Assertions.assertEquals(lastName, client.getLastName());
        Assertions.assertEquals(age, client.getAge());
        Assertions.assertEquals(dateOfBirth, client.getDateOfBirth());
    }
}
