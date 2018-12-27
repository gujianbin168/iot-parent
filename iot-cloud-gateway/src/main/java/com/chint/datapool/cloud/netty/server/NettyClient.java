package com.chint.datapool.cloud.netty.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import org.springframework.context.annotation.PropertySource;

@PropertySource({ "classpath:application.properties" })
public class NettyClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 9090);
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.write("$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$");
			printWriter.flush();
			socket.shutdownOutput();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
