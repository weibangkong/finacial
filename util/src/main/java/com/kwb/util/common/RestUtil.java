package com.kwb.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Http请求工具类
 * @author WeiBang Kong
 */
public class RestUtil {
    private static Logger logger = LoggerFactory.getLogger(RestUtil.class);

    /**
     * 发送Post请求
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url,String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            //获取请求地址
            URL realUrl = new URL(url);
            //获取请求链接
//            URLConnection conn = realUrl.openConnection();
            //设置请求属性
            HttpURLConnection httpConn = (HttpURLConnection) realUrl.openConnection();

//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("Content-type", "application/json");
//            conn.setRequestProperty("connection","keep-Alivre");
//            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            conn.setReadTimeout(18000);

            httpConn.setRequestMethod("POST");
//            httpConn.setRequestProperty("accept", "*/*");
            httpConn.setRequestProperty("Content-type", "application/json");
//            httpConn.setRequestProperty("connection","keep-Alivre");
//            httpConn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            httpConn.setReadTimeout(18000);

            //post请求必须设置
//            conn.setDoInput(true);
//            conn.setDoOutput(true);

            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            httpConn.connect();

            //获取输出流
//            conn.connect();
//            out = new PrintWriter(conn.getOutputStream());
            out = new PrintWriter(httpConn.getOutputStream());
            //输出请求参数
            out.print(param);
            //刷新输出流
            out.flush();
            //获取输入流
//            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            //输入结果
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.warn("发送post请求异常，{}", e);
            e.printStackTrace();
        } finally {
            //关闭流--先开后关
            try {
                if (null != out) {
                    out.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.warn("关闭流异常，{}",e);
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送GET请求
     * @param url
     * @param param
     * @return
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            //打开与URL的连接
            URLConnection conn = realUrl.openConnection();
            //设置通用属性
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //实际连接
            conn.connect();

//            Map<String, List<String>> map = conn.getHeaderFields();
//            map.forEach((key,value) -> System.err.println(key + " : " + value));

            //定义输入流来读取URL的相应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while (null != (line = in.readLine())) {
                result += line;
            }
        } catch (Exception e) {
            logger.warn("发送GET请求异常，{}",e);
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.warn("关闭输入流异常,{}",e);
                e.printStackTrace();
            }
        }
        return result;
    }

/*    File newFile = new File(file_path);
    File fileParent = newFile.getParentFile();
				if(!fileParent.exists()){
        fileParent.mkdirs();
    }
    //创建文件
				newFile.createNewFile();
    URL url = new URL("");
    //打开链接
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    //设置请求方式为"GET"
		        conn.setRequestMethod("GET");
    //超时响应时间为5秒
		        conn.setConnectTimeout(5 * 1000);
    //通过输入流获取图片数据
    InputStream inStream = conn.getInputStream();
    //得到图片的二进制数据，以二进制封装得到数据，具有通用性
    byte[] data = readInputStream(inStream);
    //new一个文件对象用来保存图片，默认保存当前工程根目录
    //创建输出流
    FileOutputStream outStream = new FileOutputStream(newFile);
    //写入数据
		        outStream.write(data);
    //关闭输出流
		        outStream.close();
}*/
}
