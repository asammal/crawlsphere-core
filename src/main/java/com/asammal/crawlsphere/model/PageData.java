package com.asammal.crawlsphere.model;

public class PageData {
    private int depth;
    private String title;
    private String url;
    private String text;

    public PageData(int depth, String title, String url, String text) {
        this.depth = depth;
        this.title = title;
        this.url = url;
        this.text = text;
    }

    public int getDepth() { return depth; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public String getText() { return text; }

    public void setDepth(int depth) { this.depth = depth; }
    public void setTitle(String title) { this.title = title; }
    public void setUrl(String url) { this.url = url; }
    public void setText(String text) { this.text = text; }
}
