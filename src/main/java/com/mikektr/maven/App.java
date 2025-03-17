package com.mikektr.maven;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Book[] books = {};
    public static Author[] authors = {};
    public static void main( String[] args ) throws SQLException
    {
        String login = "michal";
        String password = "kotra123";
        connectToDataBase(login,password);
        openWindow();
        sortClippings();
        
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

    private static void sortClippings(){

        
        String path = searchForClippings();
        

        //BufferedReader for faster acces to large file
        try  (BufferedReader reader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)){


            
            String line;

            //Inicialize library to store book and authors to store authors
            Library newLib = new Library();
            Authors authors = new Authors();

            

            while((line = reader.readLine()) != null){
                
                // reading and processing 'modules'
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                String titleAndAuthor = line;

                String quoteInfo = reader.readLine();

                reader.readLine();
                line = reader.readLine();
                String quote = line;
                line = reader.readLine();
                while(!line.contains("==========")){
                    quote += line;
                    line = reader.readLine();
                }

                //parsing titleAndAuthor and adding both book and author to library and authors

                int indexOpen = titleAndAuthor.lastIndexOf("(");
                int indexClose = titleAndAuthor.lastIndexOf(")");
                if(indexOpen == -1)
                    continue;
                String title = titleAndAuthor.substring(0,indexOpen);
                String author = titleAndAuthor.substring(indexOpen+1,indexClose);
                // System.out.println(title + " AUTOR: " + author);
                if(!newLib.bookExists(title)){
                    Book newBook = new Book(title,author,authors);
                    newLib.addBook(newBook);
                }
            }
            newLib.displayBooks();
            
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            } catch (UnsupportedEncodingException ex) {
        } catch (IOException ex) {
        }
        }
                
    
}
