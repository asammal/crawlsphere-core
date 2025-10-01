package com.asammal.crawlsphere;

import com.asammal.crawlsphere.model.PageData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {


    public static void writeToJson(String filePath, List<PageData> pages) {
        JSONArray jsonArray = new JSONArray();

        for (PageData page : pages) {
            JSONObject obj = new JSONObject();
            obj.put("url", page.getUrl());
            obj.put("depth", page.getDepth());
            obj.put("title", page.getTitle());
            obj.put("text", page.getText());
            jsonArray.put(obj);
        }

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Ensure folder exists
            FileWriter writer = new FileWriter(file);
            writer.write(jsonArray.toString(4)); // Pretty print
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write JSON: " + e.getMessage());
        }
    }
}
