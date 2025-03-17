package com.mikektr.maven;

import java.util.ArrayList;
import java.util.List;

public class Authors {
    private List<Author> authors = new ArrayList();
    public void addAuthor(Author Author){
        authors.add(Author);
    }

    public boolean authorExists(String name){
        return authors.stream().anyMatch(author -> author.getName().equalsIgnoreCase(name));
    }
}
