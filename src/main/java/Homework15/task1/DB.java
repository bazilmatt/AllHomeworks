package Homework15.task1;


import Homework15.task1.ConnectionManager.DBManagerIMPL;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.*;


public class DB {


    public static void renewDB(Connection connection) throws SQLException {

        try (Statement statement = connection.createStatement()) {
            statement.execute("Drop table if exists students;" +
                    "DROP table if exists article;" +

                    "CREATE TABLE IF Not exists Articles(id bigserial primary key," +
                    "author varchar," +
                    "securityLevel integer, " +
                    "label varchar);\n" +

                    "CREATE TABLE IF Not exists students(" +
                    "id bigSerial primary key, " +
                    "name varchar NOT NULL, " +
                    "SecurityLevel integer);" +

                    "INSERT INTO students (Name, SecurityLevel) " +
                    "values ('name1',1), ('name2', 2);" +
                    "INSERT INTO articles (author, securitylevel, label) " +
                    "values ('author1',2,'label1'), ('author2', 2, 'label2')");

        }
    }
}
