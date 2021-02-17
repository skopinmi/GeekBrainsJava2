package sample.server;

import java.sql.*;
import java.util.ArrayList;

public class AuthService {

    private static Connection connection;
    private static Statement statement;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:baza.db");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass (String login, String pass) {
        String sql = String.format("select nickname from baza where login = '%s' and password = '%s'", login, pass);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
//  получение черного списка из базы данных

    public static ArrayList <String> getBlackList (String nick) {
        String sql = String.format("select nicknameblack from blacklist where nickname = '%s'", nick);
        ArrayList<String> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            int i = 1;
            while (true){

                if (resultSet.next()) {
                    result.add(resultSet.getString(1));
                } else {
                    break;
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
// добавить в blacklist

    public static void addToBlackList (String nickname, String nickBlack) {
        String sql = String.format("insert into blacklist values ('%s', '%s')", nickname, nickBlack);
        try {
            statement.addBatch(sql);
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

// удалить из blacklist

    public static void deleteBlackList (String nickname, String nickBlack) {
        String sql = String.format("delete from blacklist where nickname = '%s' and nicknameblack = '%s'", nickname, nickBlack);
        try {
            statement.addBatch(sql);
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
