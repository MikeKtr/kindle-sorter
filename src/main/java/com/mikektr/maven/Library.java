package com.mikektr.maven;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {

            if(book.getAuthor() != null)
            System.out.println("->" + book.getTitle() + " - " + book.getAuthor().getName());
            else
            System.out.println("->" + book.getTitle());

        }
    }
    
    public boolean bookExists(String title) {
        return books.stream()
                .anyMatch(book -> book.getTitle().equalsIgnoreCase(title));
    }
}
