package com.chint.datapool.cloud.cassandra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
//import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.core.CassandraOperations;
//import org.springframework.data.cassandra.core.CassandraTemplate;
//import org.springframework.data.cassandra.core.convert.CassandraConverter;
//import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
//import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
//import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
//import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
//@EnableCassandraRepositories(basePackages = { "com.chint.datapool.cloud.cassandra" })
public class CassandraConfig {
//	@Autowired
//	private CassandraDataSourceConfig dataSource;
//
//	@Bean
//	public CassandraClusterFactoryBean cluster() {
//		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
//		cluster.setContactPoints(dataSource.getContactPoints());
//		cluster.setPort(dataSource.getPort());
//		return cluster;
//	}
//
//	@Bean
//	public CassandraMappingContext mappingContext() {
//		BasicCassandraMappingContext mappingContext = new BasicCassandraMappingContext();
//		mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), "mykeyspace"));
//		return mappingContext;
//	}
//
//	@Bean
//	public CassandraConverter converter() {
//		return new MappingCassandraConverter(mappingContext());
//	}

//	@Bean
//	public CassandraSessionFactoryBean session() throws Exception {
//		CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
//		session.setCluster(cluster().getObject());
//		session.setKeyspaceName("mykeyspace");
//		session.setConverter(converter());
//		session.setSchemaAction(SchemaAction.NONE);
//		return session;
//	}
//
//	@Bean
//	public CassandraOperations cassandraTemplate() throws Exception {
//		return new CassandraTemplate(session().getObject());
//	}
}
