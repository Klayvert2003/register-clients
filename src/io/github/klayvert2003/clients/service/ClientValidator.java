package io.github.klayvert2003.clients.service;

import io.github.klayvert2003.clients.domain.Client;
import io.github.klayvert2003.clients.domain.exceptions.InvalidCpfException;
import io.github.klayvert2003.clients.domain.exceptions.ObrigatoryDataException;

public class ClientValidator {
    public static void validateObrigatoryData(Client client) {
        if (client.getName() == null) {
            throw new ObrigatoryDataException("Campo nome é obrigatório!");
        }

        if (client.getGender() == null) {
            throw new ObrigatoryDataException("Campo sexo é obrigatório!");
        }

        if (client.getPhoto() == null) {
            throw new ObrigatoryDataException("Foto do cliente é obrigatório!");
        }

        if (client.getCpf().length() != 11) {
            throw new InvalidCpfException("CPF do cliente é Inválido!");
        }
    }
}
