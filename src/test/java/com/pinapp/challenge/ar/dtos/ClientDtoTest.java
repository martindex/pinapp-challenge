package com.pinapp.challenge.ar.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ClientDtoTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        int age = 30;
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        LocalDate estimatedDeathDate = LocalDate.of(2060, 1, 1);

        // Act
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setAge(age);
        clientDto.setDateOfBirth(dateOfBirth);
        clientDto.setEstimatedDeathDate(estimatedDeathDate);

        // Assert
        Assertions.assertEquals(firstName, clientDto.getFirstName());
        Assertions.assertEquals(lastName, clientDto.getLastName());
        Assertions.assertEquals(age, clientDto.getAge());
        Assertions.assertEquals(dateOfBirth, clientDto.getDateOfBirth());
        Assertions.assertEquals(estimatedDeathDate, clientDto.getEstimatedDeathDate());
    }

    @Test
    public void testConstructor() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        int age = 30;
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);

        // Act
        ClientDto clientDto = new ClientDto(firstName, lastName, age, dateOfBirth);

        // Assert
        Assertions.assertEquals(firstName, clientDto.getFirstName());
        Assertions.assertEquals(lastName, clientDto.getLastName());
        Assertions.assertEquals(age, clientDto.getAge());
        Assertions.assertEquals(dateOfBirth, clientDto.getDateOfBirth());
    }
}
