package com.dc.dms.utils;

import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by sacjoshi on 11/18/2016.
 */
public class TestDatabaseHelper {


    //private static final Logger LOGGER = Logger.getLogger(TestDatabaseHelper.class);
    private static TestDatabaseHelper INSTANCE;

    private Connection connection = null;

    public static TestDatabaseHelper getInstantce() {

        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            INSTANCE = new TestDatabaseHelper();
        }
        return INSTANCE;
    }

    private String getConnectionString() {
        String h2host = System.getProperty("system.test.h2.host");
        if (h2host == null) h2host = "localhost";
        return "jdbc:h2:tcp://" + h2host + ":1521/test";
    }

    public String getSchemaFileName() {
        return "ods-schema.sql";
    }

    public String getReferenceDataFileName() {
        return "ods-reference-data.sql";
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (this.connection != null && !connection.isClosed()) {
            return this.connection;
        }
        Class.forName("org.h2.Driver");
        this.connection = DriverManager.getConnection(this.getConnectionString(), "sa", "");
        return this.connection;
    }

    public void executeScriptFiles(List<String> scriptFiles, DataSource ds) throws Exception {
        for (String filename : scriptFiles) {
            executeScriptFile(filename, ds);
        }
    }

    public void executeScriptFiles(DataSource ds, String... scriptFiles) throws Exception {
        for (String filename : scriptFiles) {
            executeScriptFile(filename, ds);
        }
    }

    public void executeScriptFile(String filename, DataSource ds) throws IOException, SQLException, ClassNotFoundException {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        Assert.notNull(in);
        StringBuilder schemaQuery = this.buildQueryFromFile(in);
        this.execute(schemaQuery.toString(), ds);
    }

    public StringBuilder buildQueryFromFile(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder;
    }

    public void execute(String query, DataSource ds) throws SQLException, ClassNotFoundException {
        Statement stmt = ds.getConnection().createStatement();
        //LOGGER.debug(String.format("Executing Query '%s':", query));

        stmt.execute(query);
    }


}
