package com.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserDao {
    // TODO： 修改密码
    // TODO:  注销用户

    // 连接数据库
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/db_web_user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 用户名及密码
    static final String USER = "****";
    static final String PASS = "****";

    private Connection connection = null;
    public Map<String, String[]> userInformation = new HashMap<>();

    public UserDao() {
        getUsers();
    }

    public Boolean findUsers(String username, String password) {
        Iterator keys = userInformation.keySet().iterator();
        while(keys.hasNext()){
            String key = (String)keys.next();
            System.out.println(key + userInformation.get(key)[0]);
            if(username.equals(key) && password.equals(userInformation.get(key)[0])){
                return true;
            }
        }
        return false;
    }

    public Boolean deleteUser(String username, String password) {
        PreparedStatement preparedStatement;
        Statement statement = null;
        Boolean flg = false;

        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql = "DELETE FROM t_user WHERE username = (?) AND password = (?);";
            preparedStatement = connection.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            userInformation.remove(username);
            flg = true;
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(statement!=null) statement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return flg;
    }

    public void getUsers() {
        Statement statement = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println("实例化Statement对象...");
            statement = connection.createStatement();
            String sql;
            sql = "SELECT username, password, phone FROM t_user";
            ResultSet resultSet = statement.executeQuery(sql);

            // 展开结果集数据库
            while(resultSet.next()){
                // 通过字段检索
                String user = resultSet.getString("username");
                String password = resultSet.getString("password");
                String phone = resultSet.getString("phone");

                userInformation.put(user, new String[]{password, phone});
            }
            // 完成后关闭
            resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(statement!=null) statement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public Boolean createUser(String username, String password, String phone) {
        PreparedStatement preparedStatement;
        Statement statement = null;
        Boolean flg = false;

        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql = "insert into t_user(username, password, phone) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, phone);
            preparedStatement.executeUpdate();
            flg = true;
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(statement!=null) statement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        userInformation.put(username, new String[]{password, phone});
        return flg;
    }

    public String getUsernameByPhone(String phone) {
        Statement statement = null;
        String username = "";
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println("实例化Statement对象...");
            statement = connection.createStatement();
            String sql;
            sql = "SELECT username, phone FROM t_user";
            ResultSet resultSet = statement.executeQuery(sql);

            // 展开结果集数据库
            while(resultSet.next()){
                // 通过字段检索
                if (phone.equals(resultSet.getString("phone"))) {
                    username = resultSet.getString("username");
                }
            }
            // 完成后关闭
            resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(statement!=null) statement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return username;
    }

    public String[] getPasswordAndPhonenameByUser(String username) {
        Iterator keys = userInformation.keySet().iterator();
        while(keys.hasNext()){
            String key = (String)keys.next();
            if (key.equals(username)) {
                return userInformation.get(key);
            }
        }
        return new String[]{"", ""};
    }

    public Boolean changePassword(String username, String newPassword) {
        PreparedStatement preparedStatement;
        Statement statement = null;
        Boolean flg = false;

        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String sql = "UPDATE t_user SET password = (?) WHERE username = (?)";
            preparedStatement = connection.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            flg = true;
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(statement!=null) statement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        userInformation.put(username, new String[]{newPassword, userInformation.get(username)[1]});
        return flg;
    }

    public static void main(String[] args) {
        System.out.println(new UserDao().changePassword("KongDeBin", "123"));
    }

}
