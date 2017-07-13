package by.tastyfood.dao;

import java.util.Properties;
import javax.annotation.Resource;

import javax.sql.DataSource;
import org.apache.log4j.Logger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolay
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value =  "classpath:db.properties")
@ComponentScan({ "by.tastyfood" })
public class HibernateConfiguration {

	private final int MAX_UPLOAD_SIZE_CONST = 50000000;

	@Resource
	private Environment environment;

        private static final Logger log = Logger.getLogger(HibernateConfiguration.class);

	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() {
        log.error("start sessionFactory");
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
		sessionBuilder.scanPackages(new String[] { "by.tastyfood.dao" });
		sessionBuilder.addProperties(hibernateProperties());

                 log.error("session properties");

//		Properties props = sessionBuilder.getProperties();

		SessionFactory sessionFactory =      sessionBuilder.buildSessionFactory();


		return sessionFactory;
	}

//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setDefaultEncoding("utf-8");
//		commonsMultipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE_CONST);
//		return commonsMultipartResolver;
//	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		log.error("dataSource" );

        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		log.error("setDriverClassName = "+(environment.getRequiredProperty("jdbc.driverClassName")));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

//		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		properties.put("hibernate.show_sql","true");
//		properties.put("hibernate.format_sql", "true");
//		properties.put("hibernate.hbm2ddl.auto","validate");
//		properties.put("hibernate.temp.use_jdbc_metadata_defaults","false");
//        properties.put("hibernate.hbm2ddl.auto = update","update");

		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}
