package Homework15.task1.DAO;

import Homework15.task1.ConnectionManager.DBManager;
import Homework15.task1.pojo.StudentPOJO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {
    private final DBManager dbManager;

    public StudentDAO(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public static final String INSERT_INTO_STUDENT = "INSERT INTO students (Name, SecurityLevel) values ( ?, ?)";
    public static final String SELECT_FROM_STUDENT = "SELECT * FROM mobile WHERE id = ?";
    public static final String UPDATE_STUDENT = "UPDATE students SET name=?, securityLevel=? WHERE id=?";
    public static final String DELETE_FROM_STUDENT = "DELETE FROM students WHERE id=?";


    public long addStudent(StudentPOJO student) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_STUDENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getSecurityLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 2L;
        }
        return 1L;
    }
}



