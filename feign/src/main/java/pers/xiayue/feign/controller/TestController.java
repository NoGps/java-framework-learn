package pers.xiayue.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xiayue.feign.service.TestService;
import pers.xiayue.feign.service.TestServiceWrapper;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping
    public void test() throws IOException {
        //testCon();
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "8888");
        testService.test();
    }

    private void testCon() throws IOException {
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "8888");
        URL url = new URL("http://172.0.13.37:8000/auth/v1/login");

        URLConnection rulConnection = url.openConnection();// 此处的urlConnection对象实际上是根据URL的
        // 请求协议(此处是http)生成的URLConnection类
        // 的子类HttpURLConnection,故此处最好将其转化
        // 为HttpURLConnection类型的对象,以便用到
        // HttpURLConnection更多的API.如下:

        HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
        httpUrlConnection.setRequestMethod("POST");
        httpUrlConnection.setDoOutput(true);//允许写出
        httpUrlConnection.setDoInput(true);//允许读入
        httpUrlConnection.setUseCaches(false);//不使用缓存
        httpUrlConnection.setRequestProperty("Content-type", "application/json");
        httpUrlConnection.setReadTimeout(1000);
        httpUrlConnection.connect();
        httpUrlConnection.getResponseMessage();
        int status = httpUrlConnection.getResponseCode();
        OutputStream outStrm = httpUrlConnection.getOutputStream();
    }
}
