package busparser;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurableBusParser extends AbstractBusParser {
    public ConfigurableBusParser(String propertyFilePath) {
        super(readProperties(propertyFilePath));
    }

    @SneakyThrows
    private static Properties readProperties(String propertiesPath) {
        InputStream propertiesFile = new BufferedInputStream(new FileInputStream(propertiesPath));
        Properties properties = new Properties();

        properties.load(propertiesFile);

        return properties;
    }
}
