package org.skywalking.apm.testcase.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MysqlConfig {
    private static String url;
    private static String userName;
    private static String password;

    static {
        InputStream inputStream = MysqlConfig.class.getClassLoader().getResourceAsStream("/jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.print("Failed to load config");
        }

        url = properties.getProperty("mysql.url");
        userName = properties.getProperty("mysql.username");
        password = properties.getProperty("mysql.password");
    }

    public static String getUrl() {
        return url;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }
}
