import java.sql.*;
        import java.nio.file.*;
        import java.io.*;
        import java.util.*;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

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
    //private Connection con = null;
    //private Session session = null;
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = get_local_Connection()){
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

    public static Connection get_local_Connection() throws SQLException, IOException{

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
    /*
    public static Connection ssh_connection() throws SQLException{

        String sshHost = "";
        String sshuser = "";
        String dbuserName = "";
        String dbpassword = "";
        String SshKeyFilepath = "/Users/XXXXXX/.ssh/id_rsa";

        int localPort = 8740; // any free port can be used
        String remoteHost = "127.0.0.1";
        int remotePort = 3306;
        String localSSHUrl = "localhost";
        /***************//*
        String driverName = "com.mysql.jdbc.Driver";

        try {
            java.util.Properties config = new java.util.Properties();
            JSch jsch = new JSch();
            session = jsch.getSession(sshuser, sshHost, 22);
            jsch.addIdentity(SshKeyFilepath);
            config.put("StrictHostKeyChecking", "no");
            config.put("ConnectionAttempts", "3");
            session.setConfig(config);
            session.connect();

            System.out.println("SSH Connected");

            Class.forName(driverName).newInstance();

            int assinged_port = session.setPortForwardingL(localPort, remoteHost, remotePort);

            System.out.println("localhost:" + assinged_port + " -> " + remoteHost + ":" + remotePort);
            System.out.println("Port Forwarded");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        String url = "jdbc:mysql://localhost/project?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "zhenya";
        String password = "12345";

        //return DriverManager.getConnection(url, username, password);

    }
    */
    /*
    public Connection getConnection() {
        Connection con = null;

        var settings = new DbSettingsController();

        boolean useSSH = settings.getSetting(SettingKey.UseSSH).equals("true");
        String sshPort = settings.getSetting(SettingKey.SSHPort);
        String sqlIp = settings.getSetting(SettingKey.MySqlIP);
        String sqlPort = settings.getSetting(SettingKey.MySqlPort);

        if(useSSH) {
            JSch jSch = new JSch();
            try {
                this.session = jSch.getSession(settings.getSetting(SettingKey.SSHUser),
                        settings.getSetting(SettingKey.SSHHost),
                        Integer.valueOf(sshPort));
                this.session.setPassword(settings.getSetting(SettingKey.SSHPassword));
                this.session.setConfig("StrictHostKeyChecking", "no");
                this.session.connect();
                this.session.setPortForwardingL(Integer.parseInt(sshPort), sqlIp, Integer.parseInt(sqlPort));
            } catch (JSchException e) {
                e.printStackTrace();
            }
        }

        var connectionString = String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true&useSSL=false",
                sqlIp, useSSH ? sshPort : sqlPort,
                settings.getSetting(SettingKey.MySqlShema));

        var user = settings.getSetting(SettingKey.MySqlUser);
        var password = settings.getSetting(SettingKey.MySqlPassword);

        try {
            con = DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }*/

}

