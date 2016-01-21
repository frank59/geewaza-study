package com.geewaza.study.test.web.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangHeng on 2016/1/21.
 */
public class NettyUtils {

	public static final String LOCALHOST = "localhost";

	private static Logger logger = LoggerFactory.getLogger(NettyUtils.class);

	public static Server startServer(final int port) throws InterruptedException {
		final Server server = new Server(port, new ServerAdapter());
		new Thread(){
			@Override
			public void run() {
				try {
					server.start();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				} finally {
					server.shutdown();
				}
			}
		}.start();

		return server;
	}

	public static class Server {
		private int port;
		private ServerAdapter serverAdapter;
		private EventLoopGroup bossGroup;
		private EventLoopGroup workerGroup;
		private Server(int port, final ServerAdapter adapter) throws InterruptedException {
			this.port = port;
			this.serverAdapter = adapter;
			bossGroup = new NioEventLoopGroup();
			workerGroup = new NioEventLoopGroup();
		}

		public void start() throws InterruptedException {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					.option(ChannelOption.SO_KEEPALIVE, true)
					.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(serverAdapter);
				}
			});
			//绑定端口
			ChannelFuture f = bootstrap.bind(port).sync();
			//临时服务器关闭监听
			f.channel().closeFuture().sync();
		}

		public void addListenner(String key, IListenner listenner) {
			serverAdapter.addListenner(key, listenner);
		}

		public void shutdown() {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	private static class ServerAdapter extends ChannelInboundHandlerAdapter {

		private Map<String, List<IListenner>> listennerMap = new HashMap<String, List<IListenner>>();

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf in = (ByteBuf) msg;
			try {
				while (in.isReadable()) { // (1)
					byte[] req = new byte[in.readableBytes()];
					in.readBytes(req);
					String body = new String(req,"UTF-8");
					System.out.println(body);
				}
			} finally {
				ReferenceCountUtil.release(msg); // (2)
			}
//			ctx.write(msg);
//			ctx.flush();
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}

		public void addListenner(String key, IListenner listenner) {
			List<IListenner> listennerList = listennerMap.get(key);
			if (listennerList == null) {
				listennerList = new ArrayList<IListenner>();
				listennerMap.put(key, listennerList);
			}
			listennerList.add(listenner);
		}
	}

	public interface IListenner {
		public void run(Object param);
	}

	public static class CommandMessage {

		private String command;

		private String key;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getCommand() {
			return command;
		}

		public void setCommand(String command) {
			this.command = command;
		}

		@Override
		public String toString() {
			return "{" +
					"key=" + key +
					", command=" + command +
					"}";
		}
	}

	public static class MessageDecoder extends ByteToMessageDecoder {

		@Override
		protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

		}
	}

	public static class MessageEncode extends ChannelOutboundHandlerAdapter {
		@Override
		public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
			CommandMessage cm = (CommandMessage) msg;
			String message = cm.getKey() + ":" + cm.getCommand();
			ByteBuf encoded = ctx.alloc().buffer(4 * message.length());
			encoded.writeBytes(message.getBytes());
			ctx.write(encoded, promise);
		}
	}

}
