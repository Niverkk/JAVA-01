package info.niverkk.course04;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * @author JKXAING on 2021/1/22
 */
public class HttpClientDemo {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get请求
        HttpGet httpGet = new HttpGet("http://localhost");
        //发起get请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        //读取响应信息
        InputStream content = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        String str;
        while((str = reader.readLine()) != null){
            System.out.println(str);
        }
        //关闭流
        reader.close();
    }
}
