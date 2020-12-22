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

@WebServlet("/find_user")
public class FindUser extends HttpServlet {
    private UserDao userDao = new LoginServlet().userDao;

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

        PrintWriter printWriter = resp.getWriter();

        if ("".equals(username)) {
            System.out.println("查找全部用户成功");
            returnJSON = new JSONObject(userDao.userInformation);
            returnJSON.put("errMsg", "查找全部用户成功");
        } else {
            String[] user = userDao.getPasswordAndPhonenameByUser(username);
            returnJSON.put("username", username);
            returnJSON.put("password", user[0]);
            returnJSON.put("phone", user[1]);
            if ("".equals(user[0]) || "".equals(user[1])) {
                System.out.println("查找"+username+"失败");
                returnJSON.put("errMsg", "查找"+username+"失败");
            } else {
                System.out.println("查找"+username+"成功");
                returnJSON.put("errMsg", "查找"+username+"成功");
            }
        }
        printWriter.println(returnJSON.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
