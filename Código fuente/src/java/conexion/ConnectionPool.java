package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
    private final String nameBBDD = "Bookware";
    private final String USER = "sa";
    private final String PASS = "root";
    private final String port = "1433";
    private final String serverName = "LAPTOP-M2HJP1QQ\\SQLEXPRESS";
    private final String URL = "jdbc:sqlserver://"+serverName+":"+port+";databaseName="+nameBBDD;
    
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource = null;
    
    private ConnectionPool() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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


