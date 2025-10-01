package com.asammal.crawlsphere;

import org.jsoup.nodes.Document;

public class PageParser {

    public static String extractText(Document doc) {
        // Remove script and style elements
        doc.select("script, style, noscript").remove();
        doc.body();
        return doc.body().text();
    }
}
