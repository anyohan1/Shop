package com.sharp.ing.configure;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfigure {
	// @ConfigurationProperties 는 Spring Boot 에서
	// properties 파일에 정의된 프로퍼티 중 주어진 prefix 를 가지는 프로퍼티들을
	// POJO 에 매핑하여 Bean 으로 만들수 있게 해주는 어노테이션이다.
	/*
	 * @Bean
	 * 
	 * @ConfigurationProperties(prefix = "spring.datasource.hikari") public
	 * HikariConfig hikariConfig() { return new HikariConfig(); }
	 * 
	 * @Bean public DataSource dataSource() { DataSource dataSource = new
	 * HikariDataSource(hikariConfig()); return dataSource; }
	 */

	// ApplicationContext = SpringFramework의 container
	@Autowired
	private ApplicationContext applicationContext;

	// mybatis 초기화
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/*Mapper.xml"));
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	/*
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/api/**").allowCredentials(true).allowedOrigins(
	 * "http://localhost:3000"); }
	 */
}
