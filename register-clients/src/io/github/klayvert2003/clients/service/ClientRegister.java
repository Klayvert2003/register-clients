package io.github.klayvert2003.clients.service;

import io.github.klayvert2003.clients.domain.Client;
import io.github.klayvert2003.clients.utils.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientRegister implements Register<Client> {
    private List<Client> clientList = new ArrayList<>();
    @Override
    public void save(Client client) {
        ClientValidator.validateObrigatoryData(client);
        this.clientList.add(client);
        FileManager.persistFile(client.getName() + ".jpg", client.getPhoto());
    }

    @Override
    public Client find(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(Client updateObject) {

    }
}
