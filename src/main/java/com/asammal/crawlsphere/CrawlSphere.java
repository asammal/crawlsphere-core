package com.asammal.crawlsphere;

import com.asammal.crawlsphere.model.PageData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CrawlSphere {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter topic to crawl:");
        String topic = scanner.nextLine().trim().toLowerCase();

        /*System.out.println("Enter starting URLs (comma-separated):");
        String urlInput = scanner.nextLine().trim();
        scanner.close();

        String[] rawUrls = urlInput.split(",");
        List<String> startUrls = new ArrayList<>();
        for (String url : rawUrls) {
            url = url.trim();
            if (!url.isEmpty()) {
                startUrls.add(url);
            }
        }

        if (startUrls.isEmpty()) {
            System.out.println("No valid starting URLs provided. Exiting.");
            return;
        }*/

        List<String> startUrls = Arrays.asList(
                "https://en.wikipedia.org/wiki",
                "https://www.ibm.com/topics/",
                "https://www.quantamagazine.org/tag/"
        );

        Crawler crawler = new Crawler(topic);
        for (String url : startUrls) {
            crawler.crawl(url, 1);
        }

        crawler.writeResults();
        System.out.println("Crawling complete. Check output JSON.");
    }
}
