package io.github.klayvert2003.clients.service;

import io.github.klayvert2003.clients.data.ClienteDAO;
import io.github.klayvert2003.clients.domain.Client;

import java.sql.Connection;
import java.util.UUID;

public class ClientDatabaseRegister implements Register<Client> {
    private final ClienteDAO dao;

    public ClientDatabaseRegister(Connection connection) {
        this.dao = new ClienteDAO(connection);
    }

    @Override
    public void save(Client client) {
        ClientValidator.validateObrigatoryData(client);
        dao.insert(client);
    }

    @Override
    public Client find(UUID id) {
        return dao.findById(id);
    }

    @Override
    public void delete(UUID id) {
        dao.delete(id);
    }

    @Override
    public void update(Client client) {
        ClientValidator.validateObrigatoryData(client);
        dao.update(client);
    }
}
