package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
    private final String nameBBDD = "bookware";
    private final String USER = "isaias";
    private final String PASS = "isaias";
    private final String port = "3306";
    private final String host = "67.205.136.242";
    private final String URL = "jdbc:mysql://" + host + ":" + port + "/" + nameBBDD + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource = null;
    
    private ConnectionPool() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
    }
    
    public static ConnectionPool getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionPool();
            return dataSource;
        } else {
            return dataSource;
        }
    }
    
    public Connection getConnection() throws SQLException {
        return this.basicDataSource.getConnection();
    }
    
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
