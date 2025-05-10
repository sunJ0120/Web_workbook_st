import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectTests {
    @Test
    public void TestConnection() throws Exception{
        Properties props = new Properties(); //properties 생성 //class경로로 찾아오도록 한다.
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            props.load(input); //properties에 로드
            String url = props.getProperty("db.url"); //properties에거 가져옴.
            String user = props.getProperty("db.username");
            String pw = props.getProperty("db.password");

            // JDBC 연결 코드로 넘김
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pw);

            Assertions.assertNotNull(connection); //connection이 있을 경우 객체가 있다.

            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHikariCP() throws Exception{
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");

        Properties props = new Properties(); //properties 생성 //class경로로 찾아오도록 한다.
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            props.load(input); //properties에 로드
            String url = props.getProperty("db.url"); //properties에거 가져옴.
            String user = props.getProperty("db.username");
            String pw = props.getProperty("db.password");

            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(pw);

            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            HikariDataSource ds = new HikariDataSource(config);
            Connection connection = ds.getConnection();

            Assertions.assertNotNull(connection); //connection이 있을 경우 객체가 있다.

            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
