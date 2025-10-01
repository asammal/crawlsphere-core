package com.asammal.crawlsphere;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class Crawler {
    private final String topic;
    private final Set<String> visited = new HashSet<>();
    private final JsonWriter writer = new JsonWriter();
    private static final int MAX_DEPTH = 3;

    public Crawler(String topic) {
        this.topic = topic.toLowerCase();
    }

    public void crawl(String url, int depth) {
        if (depth > MAX_DEPTH || visited.contains(url)) return;
        visited.add(url);

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();

            doc.body();
            String text = doc.body().text().toLowerCase();
            doc.title();
            String title = doc.title().toLowerCase();

            boolean matches = false;
            for (String word : topic.split("\\s+")) {
                if (title.contains(word) || text.contains(word)) {
                    matches = true;
                    break;
                }
            }

            if (matches) {
                writer.addPage(depth, doc.title(), url, doc.body() != null ? doc.body().text() : "");
            } else {
                return;
            }

            String domain = getDomain(url);
            Elements links = doc.select("a[href]");
            int linkCount = 0;
            for (Element link : links) {
                String absUrl = link.absUrl("href");
                if (!absUrl.isEmpty() && absUrl.startsWith(domain) && !visited.contains(absUrl)) {
                    crawl(absUrl, depth + 1);
                    linkCount++;
                    if(linkCount > 3){
                        break;
                    }
                }
            }
        } catch (IOException | URISyntaxException ignored) { }
    }

    private String getDomain(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getScheme() + "://" + uri.getHost();
        return domain.endsWith("/") ? domain : domain + "/";
    }

    public void writeResults() {
        writer.writeToFile("output/crawlsphere_output.json");
    }
}
