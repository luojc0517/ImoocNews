package com.jackie.imoocnews;

/**
 * Created by Law on 2015/12/1.
 */
public class NewsBean {
    private String newsUrl;
    private String newsTitle;
    private String newsContent;

    public NewsBean(String newsUrl, String newsTitle, String newsContent) {
        this.newsUrl = newsUrl;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
