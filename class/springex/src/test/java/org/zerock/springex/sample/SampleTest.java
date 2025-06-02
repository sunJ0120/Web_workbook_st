package org.zerock.springex.sample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class SampleTest {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testService(){
        log.info("sampleService = {}", sampleService);
        Assertions.assertNotNull(sampleService);
    }

    @Test
    public void testConnection() throws Exception{
        Connection connection = dataSource.getConnection();
        log.info("connection test : {}", connection);
        Assertions.assertNotNull(connection);

        connection.close();
    }
}