package com.winchester.retrofit.example.http.responseBody;

public class RespSearch extends RespPacket{


    public final String lastBuildDate;
    public final String postdate;
    public final int    total;
    public final int    start;
    public final int    display;
    public final String title;
    public final String link;
    public final String description;
    public final String bloggerName;
    public final String bloggerLink;


    public RespSearch(String lastBuildDate, String postdate, int total, int start,
                      int display, String title, String link, String description,
                      String bloggerName, String bloggerLink) {
        this.lastBuildDate = lastBuildDate;
        this.postdate = postdate;
        this.total = total;
        this.start = start;
        this.display = display;
        this.title = title;
        this.link = link;
        this.description = description;
        this.bloggerName = bloggerName;
        this.bloggerLink = bloggerLink;
    }

    public String toString() {
        return "RespSearch{" +
                "lastBuildDate='" + lastBuildDate + '\'' +
                ", postdate='" + postdate + '\'' +
                ", total='" + total + '\'' +
                ", start='" + start + '\'' +
                ", display='" + display +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", bloggerName='" + bloggerName + '\'' +
                ", bloggerLink='" + bloggerLink + '\'' +
                '}';
    }
}
