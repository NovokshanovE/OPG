import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect_to_DB{
    private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String user = "zhenya";
    private static final String pass = "12345";
    /** Called when the activity is first created. */
    public void testDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String result = "Database connection success\n";
            System.out.println(result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connect_to_DB test = new connect_to_DB();
        test.testDB();
    }
}

