package com.asammal.crawlsphere.model;

public class PageData {
    private String url;
    private int depth;
    private String title;
    private String text;

    public PageData(String url, int depth, String title, String text) {
        this.url = url;
        this.depth = depth;
        this.title = title;
        this.text = text;
    }

    public String getUrl() { return url; }
    public int getDepth() { return depth; }
    public String getTitle() { return title; }
    public String getText() { return text; }
}
