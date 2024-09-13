package io.github.klayvert2003.clients.data;

import io.github.klayvert2003.clients.domain.Client;
import io.github.klayvert2003.clients.domain.enums.TypeGender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// DAO -> Data Access Object
public class ClienteDAO {

    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Client client) {
        try {
            String insertQuery = """
                INSERT INTO clients (code, name, gender, cpf, age) VALUES (?, ?, ?, ?, ?);
            """;
            PreparedStatement ps = this.connection.prepareStatement(insertQuery);
            ps.setString(1, client.getId().toString());
            ps.setString(2, client.getName());
            ps.setString(3, client.getGender().name());
            ps.setString(4, client.getCpf());
            ps.setInt(5, client.getAge());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Client client) {
        try {
            String updateQuery = """
                UPDATE clients
                    SET name = ?,
                        gender = ?,
                        cpf = ?,
                        age = ?
                WHERE
                    code = ?;
            """;
            PreparedStatement ps = this.connection.prepareStatement(updateQuery);
            ps.setString(1, client.getName());
            ps.setString(2, client.getGender().name());
            ps.setString(3, client.getCpf());
            ps.setInt(4, client.getAge());
            ps.setString(5, client.getId().toString());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID code) {
        try {
            String updateQuery = """
                DELETE FROM clients WHERE code = ?;
            """;
            PreparedStatement ps = this.connection.prepareStatement(updateQuery);
            ps.setString(1, code.toString());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> listClients() {
        List<Client> clientList = new ArrayList<>();
        try {
            String query = "SELECT * FROM clients;";
            PreparedStatement ps = this.connection.prepareStatement(query);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                String code = result.getString("code");
                String gender = result.getString("gender");
                String name = result.getString("name");
                String cpf = result.getString("cpf");
                Integer age = result.getInt("age");

                Client client = new Client();
                client.setId(UUID.fromString(code));
                client.setGender(TypeGender.valueOf(gender));
                client.setName(name);
                client.setCpf(cpf);
                client.setAge(age);

                clientList.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientList;
    }

    public Client findById(UUID code) {
        try {
            String query = "SELECT * FROM clients WHERE code = ?;";
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, code.toString());

            ResultSet result = ps.executeQuery();
            result.next();

            String findCode = result.getString("code");
            String findGender = result.getString("gender");
            String findName = result.getString("name");
            String findCpf = result.getString("cpf");
            Integer findAge = result.getInt("age");

            Client client = new Client();
            client.setId(UUID.fromString(findCode));
            client.setGender(TypeGender.valueOf(findGender));
            client.setName(findName);
            client.setCpf(findCpf);
            client.setAge(findAge);

            return client;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
