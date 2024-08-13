package com.epam.rd.autotasks.collections.map;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String title;
    private final List<String> genres;
    private final BigDecimal cost;

    public Book(String title, List<String> genres, BigDecimal cost) {
        this.title = title;
        this.genres = genres;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
               Objects.equals(genres, book.genres) &&
               Objects.equals(cost, book.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genres, cost);
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', genres=" + genres + ", cost=" + cost + "}";
    }


    @Override
    public int compareTo(Book o) {
        if (o == null) {
            throw new NullPointerException("Cannot compare to null");
        }

        int titleComparison = this.title.compareTo(o.title);
        if (titleComparison != 0) {
            return titleComparison;
        }

        // Compare costs, handling null values
        if (this.cost == null && o.cost == null) {
            return 0;
        }
        if (this.cost == null) {
            return 1; 
        }
        if (o.cost == null) {
            return -1; 
        }
        return this.cost.compareTo(o.cost);
    }
}