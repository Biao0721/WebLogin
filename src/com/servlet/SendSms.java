package com.servlet;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
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
import java.util.Random;

@WebServlet("/send_sms")
public class SendSms extends HttpServlet {
    public static final String AccessKey_ID = "******";
    public static final String Secret = "******";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
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
            String phoneNumber = jsonObject.getString("phoneNumber");
            System.out.println(phoneNumber);

            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            //设置超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            IClientProfile profile = DefaultProfile.getProfile(
                    "cn-hangzhou",
                    AccessKey_ID,
                    Secret);
            DefaultProfile.addEndpoint(
                    "cn-hangzhou",
                    "cn-hangzhou",
                    "Dysmsapi",
                    "dysmsapi.aliyuncs.com");
            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers(phoneNumber);
            request.setSignName("******");
            request.setTemplateCode("******");
            request.setTemplateParam("{\"code\":\""+verifyCode+"\"}");

            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            //将生成的验证码和创建时间放到session中，后面验证从session中取
            HttpSession session = req.getSession();
            session.setAttribute("verifyCode", verifyCode);
            session.setAttribute("verifyCodeCreateTime", System.currentTimeMillis());

            PrintWriter printWriter = resp.getWriter();
            if (sendSmsResponse.getCode().equals("OK")) {
                System.out.println("验证码以发送，请耐心等待");
                returnJSON.put("errMsg", "验证码以发送，请耐心等待");
            } else {
                System.out.println(sendSmsResponse.getCode());
                returnJSON.put("errMsg", sendSmsResponse.getCode());
            }

            printWriter.println(returnJSON.toString());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
