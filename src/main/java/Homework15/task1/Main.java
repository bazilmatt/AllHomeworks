package Homework15.task1;

import Homework15.task1.ConnectionManager.DBManager;
import Homework15.task1.ConnectionManager.DBManagerIMPL;
import Homework15.task1.DAO.StudentDAO;
import Homework15.task1.pojo.ArticlePojo;
import Homework15.task1.pojo.StudentPOJO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Start programm");
        DBManager dbManager = DBManagerIMPL.getInstance();
        DB.renewDB(dbManager.getConnection());
        Main main = new Main();
        StudentDAO studentDAO = new StudentDAO(dbManager);
        studentDAO.addStudent(main.generateStudent());
    }

    public StudentPOJO generateStudent(){
        return new StudentPOJO("Vasya", 1);
    }

    public ArticlePojo generateArticle(){
        return new ArticlePojo("article1", "Vasya");
    }
}
