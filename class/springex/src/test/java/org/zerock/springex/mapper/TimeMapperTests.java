package org.zerock.springex.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class TimeMapperTests {
    @Autowired(required = false)
    private TimeMapper2 timeMapper2;

    @Test
    public void testGetTime(){
        log.info("timeMapper2.getNow() = {}", timeMapper2.getNow());
    }
}