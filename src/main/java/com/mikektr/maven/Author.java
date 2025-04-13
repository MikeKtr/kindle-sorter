package com.mikektr.maven;

public class Author {
    
    private String name;

    public Author(String titleLine) {
        if(titleLine.lastIndexOf("(") != -1 && titleLine.lastIndexOf(")") != -1){
        this.name = titleLine.substring(titleLine.lastIndexOf("(")+1, titleLine.lastIndexOf(")"));}
        else{
            this.name = "Unknown";
        }
    }
    
    
    public String getName(){
        return name;
    }
}
