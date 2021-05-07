package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
    private final String nameBBDD = "Bookware";
    private final String USER = "";
    private final String PASS = "";
    private final String port = "3306";
    private final String host = "localhost";
    private final String instanceName = "LAPTOP-M2HJP1QQ\\SQLEXPRESS";
    //private final String URL = "jdbc:mysql://" + host + ":" + port + "/" + nameBBDD + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String URL = "jdbc:sqlserver://"+host+"\\"+instanceName+":"+port+";databaseName="+nameBBDD;
    
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


