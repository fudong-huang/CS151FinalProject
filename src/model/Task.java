package model;


import java.time.LocalDate;


/**
 * Task class
 */

public  class Task {
    String content;
    LocalDate date;

    /**
     * Constructor
     * @param content the content of Task
     * @param date the date of creation
     */
    public Task(String content, LocalDate date) {
        this.content = content;
        this.date = date;
    }

    /**
     * Constructor
     */
    public Task() {
    }

    /**
     *  Get Content
     * @return String of content
     */
    public String getContent() {
        return content;
    }

    /**
     * get Date
     * @return LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * override equals methods
     * @param b another Task
     * @return true if equal, false if not
     */
    public boolean equals(Task b) {
        if (b == null) return false;
        return (this.content.equals(b.content) && this.date.equals(b.getDate()));
    }
}
