package com.mikektr.maven;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        

        String login = "michal";
        String password = "kotra123";
        
        connectToDataBase(login,password);
        ArrayList<Book> library1 = readClippingFromFolder("C:\\Users\\micha\\Desktop\\Kindle Sorter\\kindle-sorter-app\\My_Clippings.txt");
        openWindow();
        
    }
        
    // Will open window in web app

    private static void openWindow() 
    {
        System.out.println("Window Opened");
    }

    //Connecting to DataBase and returns is connection was succesfull

    private static boolean connectToDataBase(String login,String password) throws SQLException{
        String CONN_URL = "jdbc:mysql://localhost:3306/kindle_sorter_db?serverTimezone=UTC";
        String PREPARED_STATEMENT = "SELECT * FROM users";
        try(Connection conn = DriverManager.getConnection(CONN_URL,"root","12345678");
        PreparedStatement ps = conn.prepareStatement(PREPARED_STATEMENT);
        ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                int id = rs.getInt("userID");
                String name = rs.getString("login");
                System.out.println(id + " " + name);
            }
        }
        return true;
    }

    //Automatically check if there is my_clippings.txt file somewhere on computer

    private static String searchForClippings(){
        String path = "C:\\Users\\micha\\Desktop\\Kindle Sorter\\kindle-sorter-app\\My_Clippings.txt";
        return path;
    }

    //Scans and sorts my_clipping.txt file 

    private static ArrayList<Book> readClippingFromFolder(String path){
        ArrayList<Book> library = new ArrayList<Book>();
        ArrayList<Author> authors = new ArrayList<Author>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "UTF-8"))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String titleLine = ""; 
                String dateLine = "";
                List<String> quoteList = new ArrayList<String>();


                while(!line.equals("==========")){
                    if(titleLine.isEmpty()){
                        titleLine = line;
                        
                    }
                    else if(dateLine.isEmpty()){
                        dateLine = line;
                        
                    }
                    else{
                    quoteList.add(line);}
                    line = reader.readLine();
                }


            boolean authorExists = false;
            Author author = new Author(titleLine);
            for(int i = 0 ; i < authors.size() ; i+=1){
                if(authors.get(i).getName().equals(author.getName())){
                    authorExists = true;
                }
            }
            if(!authorExists){
                authors.add(author);
            }
            
            Book book = new Book(titleLine,author);
            boolean bookExists = false;
            for(int i = 0 ; i < library.size() ; i += 1){
                if(library.get(i).getTitle().equals(book.getTitle())){
                    bookExists = true;
                }
            }
            if(!bookExists){
                library.add(book);
            }

            Quote quote = new Quote(quoteList,book);


                titleLine = "";
                dateLine = "";
                quoteList.clear();
            }


        for(int i = 0; i < library.size(); i+=1){
            System.out.println(library.get(i).getTitle());
            System.out.println(library.get(i).getAuthor().getName());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }




        return library;

    }





}