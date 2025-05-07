import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sunj.boardproject.util.ConnectionUtil;

import java.sql.Connection;

@Log4j2
public class ConnectionTest {
    @Test
    public void TestConnection() throws Exception{
        Connection conn = ConnectionUtil.INSTANCE.getConnection();

        //디버깅 로그
        log.info(conn);
        Assertions.assertNotNull(conn);
    }
}
