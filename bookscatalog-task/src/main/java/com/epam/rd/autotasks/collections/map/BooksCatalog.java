package com.epam.rd.autotasks.collections.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class BooksCatalog {
    private static final String EOL = "\n";
    private Map<Author, List<Book>> catalog;

    public BooksCatalog() {
        this.catalog = new TreeMap<>();
    }

    public BooksCatalog(Map<Author, List<Book>> catalog) {
        this.catalog = new TreeMap<>(catalog);
    }

    public List<Book> findByAuthor(Author author) {
        if (author == null) {
            throw new NullPointerException("Author cannot be null");
        }
        return catalog.get(author);
    }

    public List<Author> findAuthorsByBook(Book book) {
        if (book == null) {
            throw new NullPointerException("Book cannot be null");
        }
        List<Author> result = new ArrayList<>();
        for (Map.Entry<Author, List<Book>> entry : catalog.entrySet()) {
            Author author = entry.getKey();
            List<Book> books = entry.getValue();
            if (books.contains(book)) {
                result.add(author);
            }
        }
        return result.isEmpty() ? Collections.emptyList() : result;
    }

    public String getAllAuthors() {
        StringBuilder sb = new StringBuilder();
        for (Author author : catalog.keySet()) {
            sb.append(author).append(EOL);
        }
        return sb.toString().trim();
    }

    public Set<Book> findBooksByGenre(String pattern) {
        if (pattern == null) {
            throw new NullPointerException("Pattern cannot be null");
        }
        Set<Book> result = new TreeSet<>();
        for (List<Book> books : catalog.values()) {
            for (Book book : books) {
                for (String genre : book.getGenres()) {
                    if (genre.toLowerCase().contains(pattern.toLowerCase())) {
                        result.add(book);
                        break;
                    }
                }
            }
        }
        return result.isEmpty() ? Collections.emptySet() : result;
    }

    public Map<Book, List<Author>> findAuthorsByBookTitle(String pattern) {
        if (pattern == null) {
            throw new NullPointerException("Pattern cannot be null");
        }
        Map<Book, List<Author>> result = new TreeMap<>();
        for (Map.Entry<Author, List<Book>> entry : catalog.entrySet()) {
            Author author = entry.getKey();
            List<Book> books = entry.getValue();
            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(pattern.toLowerCase())) {
                    List<Author> authors = result.get(book);
                    if (authors == null) {
                        authors = new ArrayList<>();
                        result.put(book, authors);
                    }
                    authors.add(author);
                }
            }
        }
        return result.isEmpty() ? Collections.emptyMap() : result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        boolean firstAuthor = true;
        for (Map.Entry<Author, List<Book>> entry : catalog.entrySet()) {
            if (!firstAuthor) {
                sb.append(", ");
            }
            firstAuthor = false;
            
            sb.append(entry.getKey()).append("=[");
            
            List<Book> books = entry.getValue();
            boolean firstBook = true;
            for (Book book : books) {
                if (!firstBook) {
                    sb.append(", ");
                }
                firstBook = false;
                sb.append(book);
            }
            
            sb.append("]");
        }
        
        sb.append("}");
        return sb.toString();
    }

}