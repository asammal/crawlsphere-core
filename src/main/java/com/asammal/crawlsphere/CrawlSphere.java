package com.asammal.crawlsphere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrawlSphere {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter topic to crawl:");
        String topic = scanner.nextLine().trim().toLowerCase();

        long startTime = System.currentTimeMillis();

        List<String> startUrls = new ArrayList<>();
        InputStream inputStream = CrawlSphere.class.getClassLoader()
                .getResourceAsStream("input/seed.txt");

        if (inputStream == null) {
            System.out.println("seed.txt not found in src/main/resources/input/");
            return;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) startUrls.add(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read seed.txt: " + e.getMessage());
            return;
        }

        if (startUrls.isEmpty()) {
            System.out.println("No valid starting URLs provided. Exiting.");
            return;
        }

        Crawler crawler = new Crawler(topic);
        for (String url : startUrls) {
            crawler.crawl(url, 1);
        }

        crawler.writeResults();
        System.out.println("Crawling complete. Check output JSON.");

        System.out.println("total time taken : " + (System.currentTimeMillis() - startTime)/1000 + " ms.");
    }
}
