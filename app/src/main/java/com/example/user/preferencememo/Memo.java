package com.example.user.preferencememo;

/**
 * Created by user on 2017-04-22.
 */

public class Memo {

    private String title;
    private String content;
    private long day;
    private int percent;

    public Memo(String title, String content, long day, int percent) {
        this.title = title;
        this.content = content;
        this.day = day;
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", day=" + day +
                ", percent=" + percent +
                '}';
    }
}
