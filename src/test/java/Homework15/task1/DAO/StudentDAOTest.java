package Homework15.task1.DAO;

import Homework15.task1.ConnectionManager.DBManager;
import Homework15.task1.ConnectionManager.DBManagerIMPL;
import Homework15.task1.pojo.StudentPOJO;
import mocks.PreparedStatementMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class StudentDAOTest {
    private StudentDAO studentDAO;
    private DBManager dbManager;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatementMock preparedStatement;

    @BeforeEach
    void setUp(){
        initMocks(this);
        dbManager = spy(DBManagerIMPL.getInstance());
        connection = mock(Connection.class);
        studentDAO = spy(new StudentDAO(dbManager));
    }

    @Test
    void addStudent() throws SQLException { when(dbManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatement).when(connection).prepareStatement(StudentDAO.INSERT_INTO_STUDENT, Statement.RETURN_GENERATED_KEYS);
        int    id         = 1;
        String name = "REAL_NAME";
        int    securityLevel = 100000;
        String manufactur = "GOOGLE";
        Student mobile     = new Student() {
        };

        Long result = studentDAO.addStudent(new StudentPOJO("asd", 2));

        verify(dbManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(StudentDAO.INSERT_INTO_STUDENT);
        verify(preparedStatement, times(1)).setString(1, name);
        verify(preparedStatement, times(1)).setInt(2, securityLevel);
        verify(preparedStatement, times(1)).executeUpdate();
        assertAll("assert all",
                () -> assertEquals(1L, result),
                () -> assertNotEquals(1L, result)
        );
    }
}