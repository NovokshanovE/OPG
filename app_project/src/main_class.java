import java.sql.*;
        import java.nio.file.*;
        import java.io.*;
        import java.util.*;

class User {
    String name;
    String mail;
    String date_registration;
    String birthday;
    public User(){}
    public User(String n, String m, String dr, String b){
        name = n;
        mail = m;
        date_registration = dr;
        birthday = b;
    }
    public static void insert_User(Connection conn){
        try {
            Statement stat = conn.createStatement();
            String query = "INSERT INTO project.user (name_User, mail_User, date_registration_User, birthday_User) VALUES (?, ?, ?, ?)";
//...
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "your-id"); // 1 - порядковый номер параметра ("?") внутри запроса
            stmt.setInt(2, 456);
            stmt.setString(3, "your-id"); // 1 - порядковый номер параметра ("?") внутри запроса
            stmt.setInt(4, 456);
            stmt.executeUpdate();

            conn.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void select_User(Connection cdb){
        //cdb = getConnection();
        try {
            Statement statement = cdb.createStatement();
            System.out.println("Select_Start");
            ResultSet rs;
            rs = statement.executeQuery("SELECT name_User FROM user");
            while ( rs.next() ) {
                String Name = rs.getString("name_User");
                System.out.println(Name);
            }
            statement.close();
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
                User new_User = new User();
                System.out.println("Connection to Store DB succesfull!");
                new_User.select_User(conn);

                conn.close();
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
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        /*
        String url = "jdbc:mysql://localhost/project?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "zhenya";
        String password = "12345";
        */
        return DriverManager.getConnection(url, username, password);
    }

}

