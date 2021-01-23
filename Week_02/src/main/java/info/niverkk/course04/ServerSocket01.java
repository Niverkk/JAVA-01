package info.niverkk.course04;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞式：一次只能处理一个请求
 * @author JKXAING on 2021/1/22
 */
public class ServerSocket01 {

    public static void main(String[] args) throws IOException {
        // 创建serversocket并监听80端口
        ServerSocket serverSocket = new ServerSocket(80);

        //循环接受，一次只能处理一个，阻塞式
        while(true){
            Socket socket = serverSocket.accept();
            service(socket);
        }
    }

    private static void service(Socket socket) throws IOException {
        // 假装业务处理
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        String msg = "hello world";
        //模仿http协议规范
        pw.println("HTTP/1.1 200 OK");
        pw.println("Content-Type: text/html;charset=utf-8");
        pw.println("Content-length: " + msg.getBytes().length);
        pw.println();
        pw.println(msg);

        //关闭相应资源
        pw.close();
        socket.close();
    }
}
