package io.github.kimmking.gateway.utils;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author JKXAING on 2021/1/30
 */
public class MyHttpClientUtils {

    public static void get(String url, ChannelHandlerContext ctx) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get请求
        HttpGet httpGet = new HttpGet(url);
        //发起get请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        // 仿照老师代码写回响应给客户端
        FullHttpResponse response = null;
        byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
        response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
        //response.headers().set("Content-Type", "application/json");
        response.headers().set("Content-Type", "text/html;charset=utf-8");
        response.headers().setInt("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));

        ctx.write(response);
        ctx.flush();
        ctx.close();
    }
}
