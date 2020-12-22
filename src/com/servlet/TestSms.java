package com.servlet;

import com.dao.UserDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test_sms")
public class TestSms extends HttpServlet {
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
        String phone = jsonObject.getString("phone");
        String sms = jsonObject.getString("sms");

        PrintWriter printWriter = resp.getWriter();

        // 从session中读取验证码
        HttpSession session = req.getSession();
        try {
            if(session.getAttribute("verifyCodeCreateTime") !=null && (System.currentTimeMillis()-Long.valueOf(String.valueOf(session.getAttribute("verifyCodeCreateTime"))) )> 1000 * 60  ){
                session.removeAttribute("verifyCode");
                session.removeAttribute("verifyCodeCreateTime");
                returnJSON.put("errMsg", "已超时");
            }
            else if (session.getAttribute("verifyCode") != null && sms.equals(session.getAttribute("verifyCode"))){
                session.removeAttribute("verifyCode");
                session.removeAttribute("verifyCodeCreateTime");
                String username = userDao.getUsernameByPhone(phone);
                if ("".equals(username)) {
                    returnJSON.put("errMsg", "未找到用户");
                } else {
                    returnJSON.put("errMsg", username + "登录成功");
                }
            }else{
                returnJSON.put("errMsg", "登录失败");
            }
        }catch(Exception e){
            returnJSON.put("errMsg", "登录失败");
            e.printStackTrace();
        }

        printWriter.println(returnJSON.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
