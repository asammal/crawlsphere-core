package com.asammal.crawlsphere;

import com.asammal.crawlsphere.model.PageData;

import java.util.List;
import java.util.Scanner;

public class CrawlSphere {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter topic to crawl:");
        String topic = scanner.nextLine().trim();

        if (topic.isEmpty()) {
            System.out.println("Topic cannot be empty.");
            return;
        }

        String seedUrl = "https://www.google.com/search?q=" + topic.replace(" ", "+");
        System.out.println("Starting crawl from: " + seedUrl);

        Crawler crawler = new Crawler(3); // Depth limit = 3
        List<PageData> results = crawler.crawl(seedUrl, 1, topic);

        JsonWriter.writeToJson("output/crawlsphere_output.json", results);
        System.out.println("Crawl finished. Results saved to output/crawlsphere_output.json");
    }
}
