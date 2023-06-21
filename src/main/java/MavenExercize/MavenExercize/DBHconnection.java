package MavenExercize.MavenExercize;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class DBHconnection {

	
	private static DBHconnection instance;
    private Connection con;

    private String connection;
    private String dbName;
    private String user;
    private String password;

    private DBHconnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver caricato correttamente");
        } catch (ClassNotFoundException e) {
            System.out.println("Non è stato possibile caricare il driver");
            e.printStackTrace();
        }
        loadProperties();
    
    }

    public static synchronized DBHconnection getInstance() {
        if (instance == null) {
            instance = new DBHconnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(connection, user, password);
            System.out.println("Connessione avvenuta");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connessione chiusa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadProperties() {
        Properties properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("resources/dbpassword.properties");
            properties.load(fis);
            this.dbName = properties.getProperty("db.name");
            this.user = properties.getProperty("db.user");
            this.password = properties.getProperty("db.password");
            this.connection = "jdbc:mysql://localhost:3306/" + "test";
        } catch (IOException e) {
            System.out.println("Failed to load properties file");
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
