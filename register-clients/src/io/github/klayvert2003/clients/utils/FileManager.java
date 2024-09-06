package io.github.klayvert2003.clients.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public static void persistFile(String nome, byte[] bytes) {
        try {
            String projectPath = System.getProperty("user.dir") + "/";
            Path path = Paths.get(projectPath + nome);
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
