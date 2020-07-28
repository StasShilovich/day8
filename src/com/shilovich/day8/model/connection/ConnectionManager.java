package com.shilovich.day8.model.connection;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConnectionManager {
    private static ConnectionManager instance;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "resources\\mysql";

    public static final String URL = "database.url";
    public static final String USERNAME = "database.username";
    public static final String PASSWORD = "database.password";
    public static final String MIN_IDLE = "database.minIdle";
    public static final String MAX_IDLE = "database.maxIdle";
    public static final String MAX_OPEN_PREPARED_STATEMENTS = "database.maxOpenPreparedStatements";

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
            instance.resourceBundle = PropertyResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
