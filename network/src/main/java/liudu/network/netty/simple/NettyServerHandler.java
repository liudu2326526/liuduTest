package liudu.network.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("服务器读取线程 " + Thread.currentThread().getName());
    System.out.println("server ctx = " + ctx);
    //将 msg 转换城 Bytebuffer
    ByteBuf buf = (ByteBuf) msg;

    System.out.println("客户端发送的消息是:" + buf.toString(StandardCharsets.UTF_8));
    System.out.println("客户端地址是: " + ctx.channel().remoteAddress());
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    //将数据写入到缓存，并刷新
    //将发送的数据编码
    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端", StandardCharsets.UTF_8));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }
}
