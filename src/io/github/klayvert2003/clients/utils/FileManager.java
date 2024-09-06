package io.github.klayvert2003.clients.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public static void persistFile(String nome, byte[] bytes) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + nome);
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
