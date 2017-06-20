import helpers.*;
import org.junit.Test;
import org.apache.log4j.Logger;

import java.io.IOException;
//import java.util.logging.Logger;

//import java.util.logging.Logger;

/**
 * Created by asledzinskiy on 15.06.17.
 */
public class BasicTest {
    //private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    //Logger LOG = Logs.getLogger().logger;
    static Logger LOG = Logger.getLogger(BasicTest.class);
    @Test
    public void basicTest() throws IOException{

    LOG.info("message");
    }
}
