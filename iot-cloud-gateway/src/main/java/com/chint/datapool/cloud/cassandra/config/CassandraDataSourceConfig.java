package com.chint.datapool.cloud.cassandra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 数据源配置
 */
@Configuration
@MapperScan("com.chint.datapool.cloud.cassandra.dao.*")
@PropertySource({ "classpath:application.properties" })
public class CassandraDataSourceConfig {
	@Value("${spring.data.cassandra.keyspace-name}")
	private String keyspaceName;
	@Value("${spring.data.cassandra.schema-action}")
	private String schemaAction;
	@Value("${spring.data.cassandra.contact-points}")
	private String contactPoints;
	@Value("${spring.data.cassandra.port}")
	private int port;

	public String getKeyspaceName() {
		return keyspaceName;
	}

	public void setKeyspaceName(String keyspaceName) {
		this.keyspaceName = keyspaceName;
	}

	public String getSchemaAction() {
		return schemaAction;
	}

	public void setSchemaAction(String schemaAction) {
		this.schemaAction = schemaAction;
	}

	public String getContactPoints() {
		return contactPoints;
	}

	public void setContactPoints(String contactPoints) {
		this.contactPoints = contactPoints;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
