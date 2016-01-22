package com.geewaza.study.test.web.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by WangHeng on 2016/1/21.
 */
public class NettyUtils {

	public static final String LOCALHOST = "localhost";

	private static Logger logger = LoggerFactory.getLogger(NettyUtils.class);

	public static Server startServer(final int port) throws InterruptedException {
		final Server server = new Server(port);
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
		private EventLoopGroup bossGroup;
		private EventLoopGroup workerGroup;
		private Map<String, List<IListenner>> listennerMap = new HashMap<String, List<IListenner>>();
		private ThreadPoolExecutor executor =
				(ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

		private Server(int port) throws InterruptedException {
			this.port = port;
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
					socketChannel.pipeline().addLast(new ObjectDecoder(
							1024 * 1024,
							ClassResolvers.weakCachingConcurrentResolver(Server.this.getClass().getClassLoader())
					));
					socketChannel.pipeline().addLast(new ObjectEncoder());
					socketChannel.pipeline().addLast(new ServerAdapter(Server.this));
				}
			});
			//绑定端口
			ChannelFuture f = bootstrap.bind(port).sync();
			//临时服务器关闭监听
			f.channel().closeFuture().sync();
		}

		public void registerListenner(String key, IListenner listenner) {
			List<IListenner> listennerList = listennerMap.get(key);
			if (listennerList == null) {
				listennerList = new ArrayList<IListenner>();
				listennerMap.put(key, listennerList);
			}
			listennerList.add(listenner);
		}

		public void shutdown() {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}


		public void handleMessage(CommandMessage msg) {
			String key = msg.getKey();
			final String command = msg.getCommand();
			List<IListenner> listenners = listennerMap.get(key);
			if (listenners != null) {
				for (final IListenner listenner : listenners) {
					executor.execute(new Runnable() {
						@Override
						public void run() {
							listenner.run(command);
						}
					});
				}
			}
		}
	}

	private static class ServerAdapter extends ChannelInboundHandlerAdapter {

		private Server server;

		public ServerAdapter(Server server) {
			this.server = server;
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			try {
				CommandMessage commandMessage = (CommandMessage) msg;
				System.out.println(commandMessage);
				server.handleMessage(commandMessage);
			} finally {
				ReferenceCountUtil.release(msg); // (2)
			}
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}
	}

	public interface IListenner {
		public void run(Object param);
	}

	public static class CommandMessage implements Serializable{

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

	public static void sendCommandMessage(String host, int port, CommandMessage commandMessage) throws Exception {
		new Client(host, port).connectAndSendMessage(commandMessage);
	}

	public static class Client {
		private EventLoopGroup group;
		private String host;
		private int port;

		private Client(String host, int port) {
			this.host = host;
			this.port = port;
			group = new NioEventLoopGroup();
		}

		public void connectAndSendMessage(final CommandMessage commandMessage) throws Exception{
			//配置客户端线程组
			try{
				//配置客户端启动辅助类
				Bootstrap b=new Bootstrap();
				b.group(group).channel(NioSocketChannel.class)
						.option(ChannelOption.SO_KEEPALIVE, true)
						.handler(new ChannelInitializer<SocketChannel>() {
							@Override
							protected void initChannel(SocketChannel ch) throws Exception {
								//添加POJO对象解码器 禁止缓存类加载器
								ch.pipeline().addLast(new ObjectDecoder(1024,ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
								//设置发送消息编码器
								ch.pipeline().addLast(new ObjectEncoder());
								//设置网络IO处理器
								ch.pipeline().addLast(new OneMessageClientHandler(commandMessage));
							}
						});
				//发起异步服务器连接请求 同步等待成功
				ChannelFuture f=b.connect(host,port).sync();
				//等到客户端链路关闭
//				f.channel().closeFuture().sync();
			}finally{
				//优雅释放线程资源
				group.shutdownGracefully();
			}
		}
	}

	public static class OneMessageClientHandler extends ChannelInboundHandlerAdapter {
		private CommandMessage commandMessage;
		public OneMessageClientHandler(CommandMessage commandMessage) {
			this.commandMessage = commandMessage;
		}

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			ctx.write(commandMessage);
			ctx.flush();
		}
	}


}
