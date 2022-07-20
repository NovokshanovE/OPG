import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    String name;
    String mail;
    String date_registration;
    String birthday;
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
