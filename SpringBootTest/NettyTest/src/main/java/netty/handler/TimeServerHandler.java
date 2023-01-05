package netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 时间服务器
 *
 * @ClassName TimeServerHandler
 * @Description
 * @Author zsks
 * @Date 2021/12/27 7:26
 * @Version 1.0
 **/
public class TimeServerHandler extends ChannelInboundHandlerAdapter {


    /**
     *  channelActive是在连接时执行的操作
     *  实现在执行结束后断开连接
     **/
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        // 获得一个4字节的缓冲区
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        // ChannelFuture代表一个还未发生的IO操作，netty的所有操作都是异步的
        final ChannelFuture f = ctx.writeAndFlush(time);
        // 所以只能通过监听的方式，当操作执行完成，关闭channel
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
