package com.chint.restful;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestfulHttpClient {
	
	public static void main(String[] args) {
		String url = "http://127.0.0.1/user";
		String param = "{\"username\":\"Lily\", \"password\":\"123456\"}";
		insureResponsePost(url,param);
	}
    /**
     * 接口调用(post请求) 数据处理
     *
     * @param url
     *            请求路径 例如：http://127.0.0.1:8080/test/test
     * @param param
     *            请求参数 例如：{"user":{ "username":"Lily", "password":"123456" }}
     * @return 响应数据 例如：{ "resultId":"1" "resultMsg":"操作成功" }
     */
    public static String insureResponsePost(String url, String param) {
        PrintWriter out = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = "";
        HttpURLConnection conn = null;
        StringBuffer strBuffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod( "POST");
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(300000);
            conn.setRequestProperty("Charset", "UTF-8");
            // 传输数据为json，如果为其他格式可以进行修改
            conn.setRequestProperty( "Content-Type", "application/json");
            conn.setRequestProperty( "Content-Encoding", "utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput( true);
            conn.setDoInput( true);
            conn.setUseCaches( false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            is = conn.getInputStream();
            br = new BufferedReader( new InputStreamReader(is));
            String line = null;
            while ((line=br.readLine())!= null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            System. out.println( "发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
                if (conn!= null) {
                    conn.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}