package netty.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 时间客户端
 *
 * @ClassName TimeClient
 * @Description
 * @Author zsks
 * @Date 2021/12/27 7:32
 * @Version 1.0
 **/
public class TimeClient {

    public static void main(String[] args) throws Exception {

        String host = "127.0.0.1";
        int port = 8888;
        //用来处理I/O操作的多线程事件循环器
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //是一个启动 NIO 服务的辅助启动类
            Bootstrap b = new Bootstrap();
            // 只绑定一个group时它即作为bossGroup也作为workerGroup
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            // 可以设置这里指定的 Channel 实现的配置参数，比如现在设置的是使用TCP的长连接
            b.option(ChannelOption.SO_KEEPALIVE, true);
            //ChannelInitializer 帮助使用者配置一个新的 Channel
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            // 启动客户端
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}