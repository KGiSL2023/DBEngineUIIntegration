package com.kgisl.dbEngine;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.kgisl.dbEngine.src.dao", 
						entityManagerFactoryRef = "srcEntityManager", 
						transactionManagerRef = "srcTransactionManager")
public class PersistenceSrcAutoConfiguration {

	@Primary
	@Bean(name="srcDataSource")
	@ConfigurationProperties(prefix = "spring.src-datasource")
	public DataSource srcDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "srcEntityManager")
	public LocalContainerEntityManagerFactoryBean srcEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier("srcDataSource") DataSource sourceDataSource) {

		Map<String, String> props = new HashMap<String, String>();
//		props.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
		props.put("hibernate.hbm2ddl.auto","update");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return builder.dataSource(sourceDataSource)
				.packages("com.kgisl.dbEngine.model.source")
				.properties(props)
				.build();
	}
	
    @Primary
    @Bean(name="srcTransactionManager")
    public PlatformTransactionManager srcTransactionManager(
    		@Qualifier("srcEntityManager") EntityManagerFactory srcEntityManager) {

        return new JpaTransactionManager(srcEntityManager);
    }

}
