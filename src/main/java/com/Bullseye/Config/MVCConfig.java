package com.Bullseye.Config;

import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.Bullseye")
public class MVCConfig extends WebMvcConfigurerAdapter
{
    // Configuring The View Resolver So Spring Can Handle JSP's
    @Bean
    public ViewResolver viewResolver()
    {
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setViewClass(JstlView.class);
	viewResolver.setPrefix("/WEB-INF/JSP/");
	viewResolver.setSuffix(".jsp");
        
	return viewResolver;
    }

    // Configuring Static Resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Resources/**").addResourceLocations("/Resources/");
    }
    
    // Used To Hash Passwords
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SessionFactory sessionFactory()
    {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("com.Bullseye");
        builder.addProperties(getHibernationProperties());

        return builder.buildSessionFactory();
    }
    
    /*
        Need an install page or something to flip this value from create on first
        run and valdidate there on out.
    */
    private Properties getHibernationProperties()
    {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.hbm2ddl.auto", "create"); // CONFIG  [validate or create ]
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // CONFIG

        return prop;
    }
    
    // Declaring Our DataSource (Database)
    @Bean(name = "dataSource")
    public BasicDataSource dataSource()
    {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver"); // CONFIG
        ds.setUrl("jdbc:mysql://localhost:3306/Bullseye"); // CONFIG [ You can use derby with local db in your ide, but I like PHP myadmin so I choose to use that ]
        ds.setUsername("testuser"); // CONFIG [ This user, dont use a root acc plzzz ]
        ds.setPassword("password"); // COFNIG [ So secure I know ]

        return ds;
    }

    // Binds A Hibernate Session From SessionFactory To A Thread
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s)
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
    
    // Used For JSR303 Constraints {@Valid..etc}.. Drops Messages To The Front
    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
}