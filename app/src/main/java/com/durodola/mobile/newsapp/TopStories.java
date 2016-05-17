package com.durodola.mobile.newsapp;

/**
 * Created by mobile on 2016-05-16.
 */
public class TopStories {

    private String item = "item";
    private String title = "title";
    private String link = "link";
    private String description = "description";
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Title/n"+title+ "Links/n"+link+"description/n"+description;
    }


}
