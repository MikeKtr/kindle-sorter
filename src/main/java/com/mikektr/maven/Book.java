package com.mikektr.maven;

public class Book {

    private String title;
    private Author author;
    private String[] quotes;
    private String titlePage;
    
    public Book(String Title,Author Author){

        if(Title.lastIndexOf("(") != -1){
        title = Title.substring(0, Title.lastIndexOf("("));}
        else{title = Title;}
        author = Author;
        
    }
    
    public Author getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String Title){
        this.title = Title;
    }
}
