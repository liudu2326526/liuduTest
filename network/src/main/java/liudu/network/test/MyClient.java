package liudu.network.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author liudu
 * @title: MyClient
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/4/29下午5:27
 */
public class MyClient {

  public static void main(String[] args) {

    String serverName = "localhost";
    int port = 6666;
    try {
      System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
      Socket client = new Socket(serverName, port);
      System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
      OutputStream outToServer = client.getOutputStream();
      DataOutputStream out = new DataOutputStream(outToServer);

      out.writeUTF("Hello from " + client.getLocalSocketAddress());
      InputStream inFromServer = client.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);
      System.out.println("服务器响应： " + in.readUTF());
      client.close();
    }catch (IOException e){
      e.printStackTrace();
    }

  }

}
