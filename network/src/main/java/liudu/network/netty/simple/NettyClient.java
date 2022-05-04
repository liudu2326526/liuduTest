package liudu.network.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

  public static void main(String[] args) throws InterruptedException {
    //只需要一个事件循环组
    NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

    try {
      //创建一个客户端启动对象
      Bootstrap bootstrap = new Bootstrap();

      bootstrap.group(eventExecutors)
          .channel(NioSocketChannel.class)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
            }
          });

      System.out.println("client is ok");

      ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();

      channelFuture.channel().closeFuture().sync();
    } finally {
      eventExecutors.shutdownGracefully();
    }
  }

}
