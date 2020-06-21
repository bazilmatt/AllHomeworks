package Homework16;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.*;


public class DB {
    static Map<String, Integer> secureMap = new HashMap<>();
    static Logger logger = LogManager.getLogger(DB.class);
    public static void main(String[] args)  {




        for (int i = 0; i < 10 ; i++) {
            secureMap.put("User"+i, 1);
        }
       // Set set = secureMap.entrySet();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "11111")) {
            renewDB(connection);
            resultSetQuery(connection);
            parametrizedQuery(connection);
            manualCommit(connection);

        } catch (SQLException throwables) {
            logger.throwing(throwables);
            throwables.printStackTrace();
        }
    }

    public static void resultSetQuery(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                System.out.println("id= " + resultSet.getInt("id"));
                System.out.println("name= "+ resultSet.getString("name"));
            }
        }
    }

    public static void parametrizedQuery(Connection connection) throws SQLException{
        try(PreparedStatement prepareStatement = connection.prepareStatement("SELECT * from users where id = ?")){
            prepareStatement.setInt(1,2);
            prepareStatement.addBatch();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (Name, SecurityLevel) values (?,?)")){
            preparedStatement.setString(1,"addedName5");
            preparedStatement.setInt(2, 10);
            preparedStatement.addBatch();
            for (int i=0; i<10; i++){       //todo как добавлять из мапы?
                preparedStatement.setString(1,"User"+i);
                preparedStatement.setInt(2, 4);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            logger.info("batch выполнен");
        }
    }

    public static void manualCommit(Connection connection) throws SQLException {
        Savepoint savepoint = null;
        try(Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            statement.execute("INSERT INTO users (Name, SecurityLevel) VALUES ('admin' , 999)");
            savepoint = connection.setSavepoint("aaaa");
            //выбрасываем exception
            statement.execute("INSERT INTO users (Name, Scurity) VALUES ('хацкер', 100500)");
            connection.commit();
            logger.info("Ручной коммит выполнен");
            connection.setAutoCommit(true);
            
        } catch (SQLException throwables) {
            connection.rollback(savepoint);
            logger.error("откат при получении исключения, admin при этом добавится");
            connection.commit();
            logger.info("commit после отката");
            //throwables.printStackTrace(); 
        }


    }



    public static void renewDB(Connection connection) throws SQLException {

        try (Statement statement = connection.createStatement()) {
            statement.execute("Drop table if exists users;" +
                    "DROP table if exists blog;" +

                    "CREATE TABLE IF Not exists Blog(id bigserial primary key," +
                    "author varchar," +
                    "securityLevel integer, " +
                    "label varchar," +
                    "text varchar);\n" +

                    "CREATE TABLE IF Not exists users(" +
                    "id bigSerial primary key, " +
                    "name varchar NOT NULL, " +
                    "SecurityLevel integer);" +

                    "INSERT INTO users (Name, SecurityLevel) " +
                    "values ('name1',1), ('name2', 2);" +
                    "INSERT INTO blog (label, text, securitylevel) " +
                    "values ('article1','text1',1), ('article2', 'text2', 2)");

        }


    }


}
