package mocks;

import Homework15.task1.ConnectionManager.DBManager;

import java.sql.Connection;

public class DBManagerMock implements DBManager{

    @Override
    public Connection getConnection() {
        return null;
    }
}
