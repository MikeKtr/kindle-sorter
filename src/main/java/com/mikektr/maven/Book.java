package com.mikektr.maven;

public class Book {

    private String title;
    private Author author;
    private String[] quotes;
    private String titlePage;
    
    public Book(String Title,String Author,Authors authors){
        title = Title;
        
        if(!authors.authorExists(Author)){
            Author newAuthor = new Author(Author);
            authors.addAuthor(newAuthor);
            this.author = newAuthor;
        }
        

    }
    
    public Author getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }
}
