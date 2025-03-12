package config;

import Repository.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class properties {
    private final Map<String, String> properties = new HashMap<>();

    public properties(String filepath) {
        loadProperties(filepath);
    }

    private void loadProperties(String filepath) {
        try (FileInputStream fis = new FileInputStream(filepath)) {
            java.util.Properties prop = new java.util.Properties();
            prop.load(fis);

            for (String key : prop.stringPropertyNames()) {
                properties.put(key, prop.getProperty(key).replace("\"", "").trim());
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului de proprietăți: " + filepath);
        }
    }

    public String getRepositoryType() {
        String repoType = properties.get("Repository");
        if (repoType == null) {
            System.out.println("Lipsește tipul de repository în fișierul de proprietăți.");
        }
        return repoType;
    }

    public String getManuscriptFile() {
        String ManuscriptFile = properties.get("ManuscriptFile");
        if (ManuscriptFile == null) {
            System.out.println("Lipsește fișierul pentru man în fișierul de proprietăți.");
        }
        return ManuscriptFile;
    }
    public String getPresentationFile() {
        String PresentationFile = properties.get("PresentationFile");
        if (PresentationFile == null) {
            System.out.println("Lipsește fișierul pentru pren în fișierul de proprietăți.");
        }
        return PresentationFile;
    }
}