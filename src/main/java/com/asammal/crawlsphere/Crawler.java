package com.asammal.crawlsphere;

import com.asammal.crawlsphere.model.PageData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Crawler {

    private final int maxDepth;
    private final Set<String> visitedUrls = new HashSet<>();

    public Crawler(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public List<PageData> crawl(String url, int depth, String topic) {
        List<PageData> pages = new ArrayList<>();
        if (depth > maxDepth || visitedUrls.contains(url)) return pages;

        try {
            visitedUrls.add(url);
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                            "(KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36")
                    .timeout(10000)
                    .get();

            String title = doc.title();
            String text = PageParser.extractText(doc);

            pages.add(new PageData(url, depth, title, text));

            // Only follow links containing the topic
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String absUrl = link.absUrl("href");
                if (!absUrl.isEmpty() && absUrl.contains(topic.replace(" ", "-"))) {
                    pages.addAll(crawl(absUrl, depth + 1, topic));
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to fetch: " + url + " | " + e.getMessage());
        }

        return pages;
    }
}
