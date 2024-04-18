import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestableDatabaseConnection {

    public static Connection getConnection()  {
        Properties prop = new Properties();

        try {
            prop.load(TestableDatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties"));
            return DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("user"),
                    prop.getProperty("password")
            );
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
