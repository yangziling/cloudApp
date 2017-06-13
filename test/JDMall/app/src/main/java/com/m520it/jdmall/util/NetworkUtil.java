package com.m520it.jdmall.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * @author 杨飞
 * @time 2016/9/1  18:47
 * @desc ${TODD}
 */
public class NetworkUtil {


    //查看后台接口 文档   最好再创建常量类
    public static String doPost(String urlPath, HashMap<String, String> params) {
        BufferedReader reader = null;
        String result = "";
        try {

            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //声明 请求的 方式
            conn.setRequestMethod("POST");
            //设置两个请求头.1.设置他是form表单提交 设置请求参数的内容长度
            //网服务器里面发送数据
            conn.setDoOutput(true);//手机的内存为参考
            conn.getOutputStream().write(parseParams(params).getBytes());

            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //最后需要关流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return result;
    }

    //转化参数

    private static String parseParams(HashMap<String, String> params) {
        String paramsStr = "";
        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            // &username=sxzsd&pwd=asdad&pwd=asdad  用户名和密码
            paramsStr += "&" + entry.getKey() + "=" + entry.getValue();
        }
        //对返回的额paramsStr截取
        paramsStr = paramsStr.substring(1);
        return paramsStr;
    }

    public static String doGet(String urlPath) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                return reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
