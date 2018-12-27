package com.chint.datapool.cloud.safe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
@Configuration
public class XssJsonStart {
	/**
	 * 描述 : xssObjectMapper
	 *
	 * @param builder builder
	 * @return xssObjectMapper
	 */
	@Bean
	@Primary
	public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
	 //解析器
	 ObjectMapper objectMapper = builder.createXmlMapper(false).build();
	 //注册xss解析器
	 SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
	 xssModule.addSerializer(new XssStringJsonSerializer());
	 objectMapper.registerModule(xssModule);
	 //返回
	 return objectMapper;
	}
}
