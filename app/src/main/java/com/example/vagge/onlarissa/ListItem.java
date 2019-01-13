package com.example.vagge.onlarissa;


public class ListItem {
    private String title;
    private String desc;
    private String img;
    private String url;
    private String date;

    public ListItem(String title, String desc, String img, String url, String date) {
        this.title = title;
        this.desc = desc;
        this.img = img;
        this.url = url;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
