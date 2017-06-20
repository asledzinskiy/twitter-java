package helpers;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * Created by asledzinskiy on 15.06.17.
 */
public class Logs {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Logs singleton = null;
    private Logs(){}

    static public void setup() {

        // get the global logger to configure it

        logger.setLevel(Level.INFO);
        try {
            fileTxt = new FileHandler("Logging.txt");
        }
        catch(IOException e){}
        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

    }

    public synchronized static Logs getLogger() {
        if(singleton == null) {
            Logs.setup();
            singleton = new Logs();
        }
        return singleton;
    }

}
