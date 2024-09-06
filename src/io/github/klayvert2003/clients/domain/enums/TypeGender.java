package io.github.klayvert2003.clients.domain.enums;

public enum TypeGender {
    M("Masculino"),
    F("Feminino"),
    O("Outros");

    private final String description;

    TypeGender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
