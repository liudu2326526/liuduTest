package liudu.network.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

  public static void main(String[] args) throws IOException {
    //创建一个线程池
    ExecutorService threadPool = Executors.newCachedThreadPool();

    //创建一个 server socket
    ServerSocket serverSocket = new ServerSocket(6666);

    System.out.println("服务器启动了");

    while (true) {

      // 监听，等待客户端连接
      System.out.println("等待连接");
      final Socket socket = serverSocket.accept();
      System.out.println("连接到一个客户端");

      //创建一个线程与之通信
      //如果有客户端链接，就创建一个线程
      threadPool.execute(new Runnable() {
        @Override
        public void run() {
          //可以和客户端通信
          handler(socket);
        }
      });

      // 客户端 telnet 127.0.0.1 6666

    }


  }

  //编写一个 handler 方法和客户端通信
  public static void handler(Socket socket) {

    // 通过 socket 获取输入流
    try {

      byte[] bytes = new byte[1024];
      InputStream inputStream = socket.getInputStream();
      //循环读取客户端发送的数据
      while (true) {
        System.out.printf("线程信息:%s 线程名字:%s%n", Thread.currentThread().getId(),
            Thread.currentThread().getName());
        System.out.println("reading.....");
        int read = inputStream.read(bytes);
        if (read != -1) {
          System.out.println(new String(bytes, 0, read));//输出客户端发送的数据
        } else {
          break;
        }
      }


    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      System.out.println("关闭和 client 的连接");
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }


  }

}
