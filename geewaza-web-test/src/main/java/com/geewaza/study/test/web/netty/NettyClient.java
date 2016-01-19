package com.geewaza.study.test.web.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by WangHeng on 2016/1/19.
 */
public class NettyClient {

	public void connect(String host, int port) throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(workerGroup)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.SO_KEEPALIVE, true);
			boot.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(new NettyClientHandler());
				}
			});

			ChannelFuture f = boot.connect(host, port).sync();
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}


	public static void main(String[] args) throws InterruptedException {
		NettyClient client = new NettyClient();
		client.connect("127.0.0.1", 8800);
	}

	private static class NettyClientHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf result = (ByteBuf) msg;
			byte[] result1 = new byte[result.readableBytes()];
			result.readBytes(result1);
			result.release();
			ctx.close();
			System.out.println("Server said:" + new String(result1));
		}

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			String msg = "Are you ok?";
			ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
			encoded.writeBytes(msg.getBytes());
			ctx.write(encoded);
			ctx.flush();
		}
	}

}
