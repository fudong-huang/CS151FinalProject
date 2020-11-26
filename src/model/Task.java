package model;


import java.time.LocalDate;

public  class Task {
    String content;
    LocalDate date;

    public Task(String content, LocalDate date) {
        this.content = content;
        this.date = date;
    }

    public Task() {
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }
}
