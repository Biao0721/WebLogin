package com.servlet;

import com.dao.UserDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public static UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        JSONObject returnJSON = new JSONObject();

        // 读取前端json数据
        BufferedReader bufferedReader = req.getReader();
        String str, jsonString = "";
        while ((str = bufferedReader.readLine()) != null) {
            jsonString +=  str;
        }
        JSONObject jsonObject = new JSONObject(jsonString);
        System.out.println(jsonObject.toString());
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        PrintWriter printWriter = resp.getWriter();
        if (userDao.findUsers(username, password)) {
            System.out.println(username + ": 登录成功");
            returnJSON.put("errMsg", username + ": 登录成功");
        } else {
            System.out.println("密码错误");
            returnJSON.put("errMsg", "密码错误");
        }
        printWriter.println(returnJSON.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
