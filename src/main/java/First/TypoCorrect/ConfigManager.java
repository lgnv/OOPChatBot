package First.TypoCorrect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    public static String getProperty(String key) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("src/main/config/keys.ini")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(key);
    }
}
