package busparser;

import lombok.SneakyThrows;

import java.util.Properties;

/**
 * Default implementation of BusParser
 * @author Zhuravlev N.O.
 * @version 0.0.1
 * @see BusParser
 */


public class DefaultBusParser extends AbstractBusParser {
    private static final DefaultBusParser instance = new DefaultBusParser();

    private DefaultBusParser() {
        super(readDefaultProperties());
    }

    public static DefaultBusParser getInstance() {
        return instance;
    }

    @SneakyThrows
    private static Properties readDefaultProperties() {
        Properties currentProperties = new Properties();

        currentProperties.load(ClassLoader.getSystemResourceAsStream("default.properties"));

        return currentProperties;
    }
}
