package com.pinapp.challenge.ar.dtos;

import java.time.LocalDate;

public class ClientDto {
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate dateOfBirth;

    public ClientDto() {
    }

    public ClientDto(String firstName, String lastName, int age, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
