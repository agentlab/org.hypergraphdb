/**
 *
 */
package org.hypergraphdb.example;

public class Book {
    protected int yearPublished;
    protected String title;
    protected String author;

    /**
     * @param string
     * @param string2
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * @param i
     */
    public void setYearPublished(int i) {
        yearPublished = i;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}