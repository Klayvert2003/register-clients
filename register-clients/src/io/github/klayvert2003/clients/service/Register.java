package io.github.klayvert2003.clients.service;

import java.util.UUID;

public interface Register<T> {
    void save(T registerObject);
    T find(UUID id);
    void delete(UUID id);
    void update(T updateObject);
}
