package org.skywalking.apm.testcase.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLExecute {
    private Connection connection;

    public SQLExecute() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //
        }
        connection = DriverManager.getConnection(MysqlConfig.getUrl(), MysqlConfig.getUserName(), MysqlConfig.getPassword());
    }

    public void createTable(String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void insertData(String sql, String id, String value) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, value);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public String queryData(String sql, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String value = resultSet.getString("value");
        preparedStatement.close();
        return value;
    }

    public void deleteData(String sql, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void dropTable(String sql) throws SQLException {
        Statement preparedStatement = connection.createStatement();
        preparedStatement.execute(sql);
        preparedStatement.close();
    }

    public void closeConnection() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
