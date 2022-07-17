
import java.sql.*;
        import java.nio.file.*;
        import java.io.*;
        import java.util.*;

public class connection_DB{

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()){

                System.out.println("Connection to Store DB succesfull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static Connection getConnection() throws SQLException, IOException{

        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String url = "jdbc:mysql://localhost/test?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "zhenya";
        String password = "12345";

        return DriverManager.getConnection(url, username, password);
    }
}