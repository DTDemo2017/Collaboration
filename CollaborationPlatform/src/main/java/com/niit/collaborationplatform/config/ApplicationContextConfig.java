package com.niit.collaborationplatform.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationplatform.model.Blog;
import com.niit.collaborationplatform.model.BlogComment;
import com.niit.collaborationplatform.model.Forum;
import com.niit.collaborationplatform.model.ForumComment;
import com.niit.collaborationplatform.model.Friend;
import com.niit.collaborationplatform.model.Job;
import com.niit.collaborationplatform.model.JobApplication;
import com.niit.collaborationplatform.model.Users;



@Configuration						
@ComponentScan("com.niit.collaborationplatform")		
//@ComponentScan(basePackages = "com.niit.binder", excludeFilters = @Filter(type = FilterType.ANNOTATION, value = AppConfig.class))
@EnableTransactionManagement		
public class ApplicationContextConfig {
	@Bean(name="dataSource")			
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");		
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");		
		dataSource.setUsername("SA");		
		dataSource.setPassword("SA");		
		

		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.setProperty("hibernate.format_sql", "true");
		connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
		dataSource.setConnectionProperties(connectionProperties);		
		return dataSource;                                    
	}
	
	@Autowired		
	@Bean(name = "sessionFactory")			
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		
		//specify all the model classes... 
		sessionBuilder.addAnnotatedClass(Users.class);			
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
			
		sessionBuilder.addAnnotatedClass(Friend.class);	
		sessionBuilder.addAnnotatedClass(Job.class);		
		sessionBuilder.addAnnotatedClass(Forum.class);	
		sessionBuilder.addAnnotatedClass(ForumComment.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		
		
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired		
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	
	return transactionManager;
	}
	
	/*@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}*/
	
	/*@Autowired
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDetailsDAO(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}*/
}

