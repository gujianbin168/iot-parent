package com.chint.datapool.cloud;

import javax.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.chint.datapool.cloud.netty.server.DiscardServer;

@SpringBootApplication
@ComponentScan
@MapperScan("com.chint.datapool.cloud.dao")
public class DataPoolApplication implements CommandLineRunner {
	@Resource
	private DiscardServer discardServer;
	
	public static void main(String[] args) {
		SpringApplication.run(DataPoolApplication.class, args);
	}
	
    @Override
    public void run(String... strings)throws Exception {
    	discardServer.run(9090);  //netty 端口
    }
 
}