package io.github.klayvert2003.clients.domain;

import io.github.klayvert2003.clients.domain.enums.TypeGender;

import java.util.Arrays;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private String cpf;
    private TypeGender gender;
    private byte[] photo;

    public Client() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TypeGender getGender() {
        return gender;
    }

    public void setGender(TypeGender gender) {
        this.gender = gender;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", gender=" + gender +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}
