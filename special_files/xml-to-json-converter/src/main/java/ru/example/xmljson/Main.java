package ru.example.xmljson;

import com.google.gson.GsonBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<ru.example.xmljson.Employee> list = XMLParser.parseXML("data.xml");
            String json = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(list);

            Files.writeString(Path.of("data2.json"), json);
            System.out.println("XML to JSON конвертация успешно завершена!");
        } catch (Exception e) {
            System.err.println("Ошибка при конвертации:");
            e.printStackTrace();
        }
    }
}