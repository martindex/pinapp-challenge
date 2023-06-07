package com.pinapp.challenge.ar.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientStatsDtoTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        double averageAge = 35.5;
        double standardDeviation = 5.2;

        // Act
        ClientStatsDto clientStatsDto = new ClientStatsDto(averageAge, standardDeviation);

        // Assert
        Assertions.assertEquals(averageAge, clientStatsDto.getAverageAge());
        Assertions.assertEquals(standardDeviation, clientStatsDto.getStandardDeviation());
    }

    @Test
    public void testConstructor() {
        // Arrange
        double averageAge = 35.5;
        double standardDeviation = 5.2;

        // Act
        ClientStatsDto clientStatsDto = new ClientStatsDto(averageAge, standardDeviation);

        // Assert
        Assertions.assertEquals(averageAge, clientStatsDto.getAverageAge());
        Assertions.assertEquals(standardDeviation, clientStatsDto.getStandardDeviation());
    }
}
