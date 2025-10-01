package com.asammal.crawlsphere;

import com.asammal.crawlsphere.model.PageData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter {
    private final List<PageData> pages = new ArrayList<>();

    public void addPage(int depth, String title, String url, String text) {
        pages.add(new PageData(depth, title, url, text));
    }

    public void writeToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(pages, writer);
        } catch (IOException e) {
            System.out.println("Failed to write JSON: " + e.getMessage());
        }
    }
}
