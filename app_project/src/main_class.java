import java.sql.*;
        import java.nio.file.*;
        import java.io.*;
        import java.util.*;

class User {
    //String name;
    //String mail;
    //String date_registration;
    //String birthday;
    public User(){}
    public static void insert_User(){

    }
    public static void select_User(Connection cdb){
        //cdb = getConnection();
        try {
            Statement statement = cdb.createStatement();
            System.out.println("Insert_Start");
            ResultSet rs;
            rs = statement.executeQuery("SELECT name_User FROM user");
            while ( rs.next() ) {
                String Name = rs.getString("name_User");
                System.out.println(Name);
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }

    }
}
class Point {


}
public class main_class{

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()){
               // conn.close();
                User User_1 = new User();
                System.out.println("Connection to Store DB succesfull!");
                User_1.select_User(conn);
                conn.close();
            }

        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }


    }

    public static Connection getConnection() throws SQLException, IOException{

        //Properties props = new Properties();
        //try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
        //    props.load(in);
        //}
        String url = "jdbc:mysql://localhost/project?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "zhenya";
        String password = "12345";

        return DriverManager.getConnection(url, username, password);
    }
    /*
    public static void select_User(Connection cdb){
        //cdb = getConnection();
        try {
            Statement statement = cdb.createStatement();
            System.out.println("Insert_Start");
            ResultSet rs;
            rs = statement.executeQuery("SELECT name_User FROM user");
            while ( rs.next() ) {
               String Name = rs.getString("name_User");
                System.out.println(Name);
             }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }

    }
    public static void insert_User(){

    }
    public static void insert_Point(){

    }*/
}

