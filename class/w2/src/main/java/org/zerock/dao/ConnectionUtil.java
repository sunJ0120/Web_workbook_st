package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public enum ConnectionUtil {
    INTSTANCE;

    private HikariDataSource ds;

    ConnectionUtil(){
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

            ds = new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws Exception{
        return ds.getConnection();
    }
}
