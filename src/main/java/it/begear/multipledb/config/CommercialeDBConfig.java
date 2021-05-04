package it.begear.multipledb.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "commercialeEntityManagerFactory", basePackages = {
		"it.begear.multipledb.commerciale.repository" }, transactionManagerRef = "commercialetransactionManager")
public class CommercialeDBConfig {

	@Bean(name = "datasource2")
	@ConfigurationProperties(prefix = "spring.commerciale.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "commercialeEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("datasource2") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.commerciale", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties).packages("it.begear.multipledb.commerciale.entity")
				.persistenceUnit("commerciale").build();
	}

	@Bean(name = "commercialetransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("commercialeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
