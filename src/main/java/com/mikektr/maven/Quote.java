package com.mikektr.maven;

import java.util.List;

public class Quote {
    String Quote;
    Book book;

    public Quote(List<String> Quote,Book book){
        for(int i = 0;i < Quote.size();i+=1){
            this.Quote = this.Quote + " " + Quote.get(i);
        }
        this.book = book;
    }
    
}
