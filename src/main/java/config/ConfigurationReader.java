package config;

import java.io.*;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationReader {

    public static ConfigurationObject read(File file) {

        ObjectMapper objectMapper = new ObjectMapper();
        ConfigurationObject configuration = new ConfigurationObject();
        try (
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                configuration = objectMapper.readValue(currentLine, ConfigurationObject.class);

            }
        } catch (JsonMappingException | FileNotFoundException e) {
            System.out.println("Error " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());

        }
        return configuration;
    }
}
