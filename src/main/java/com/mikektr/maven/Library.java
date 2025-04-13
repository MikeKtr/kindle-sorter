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
            System.out.println("-> " + book.getTitle() + " - " + book.getAuthor().getName());
            else
            System.out.println("-> " + book.getTitle());

        }
    }

    public static Book get(int i){
        return books.get(i);
    }

    public static int size(){
        return books.size();
    }
    void removeName(Book book){
        String title = book.getTitle().toLowerCase();
        if(book.getAuthor() != null){
        String author = book.getAuthor().getName().toLowerCase();
        if(title.contains(author)){
            title = title.substring(0,title.indexOf(author)) + title.substring(title.indexOf(author)+author.length());
        }
        book.setTitle(title);
        }}
    
    void removeSpaces(Book book){
        String title = book.getTitle().toLowerCase();
        title = title.replace("_"," ");
        title = title.replace("-"," ");
        title = title.trim().replaceAll(" +"," ");
        // System.out.println(title);
        book.setTitle(title);
    }

    
    //In this function you implement methods that make titles cleaner (eg. removing author's name from title)

    void clearLibrary(){
        for(Book book : books){
            removeName(book);
            removeSpaces(book);
        }
    }


    void swapBook(int i,int j){
        Book book1 = books.get(i);
        books.set(i,books.get(j));
        books.set(j,book1);
    }

    public int findPivot(List<Book> books,int p,int k){
        int q = (p+k)/2;
        int i = p - 1;
        int j = k + 1;
        while(true){
            i+=1;
            while(books.get(i).getTitle().compareToIgnoreCase(books.get(q).getTitle()) <0){
                i+=1;
            }
            j-=1;
            while(books.get(j).getTitle().compareToIgnoreCase(books.get(q).getTitle()) >0){
                j-=1;
            }

            if(i>=j){
                return j;
            }
            swapBook(i, j);
        }
        

        
    }
    //TODO 1: fix quicksort (only kinda sorts :) )
    public void quickSortBooks(List<Book> books,int p,int k){
        if(p < k){
        int pivot = findPivot(books,p,k);
        quickSortBooks(books, p, pivot);
        quickSortBooks(books, pivot + 1, k);}
    }

    public void sortBooks(){
        quickSortBooks(books,0,books.size()-1);
    }

    public boolean bookExists(String title) {
        return books.stream()
                .anyMatch(book -> book.getTitle().equalsIgnoreCase(title));
    }
}
