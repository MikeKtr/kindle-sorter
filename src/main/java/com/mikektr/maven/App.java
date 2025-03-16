package com.mikektr.maven;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

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
        openWindow();
        connectToDataBase(login,password);

    }
        
    private static void openWindow() 
    {
        System.out.println("Window Opened");
    }

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
}
