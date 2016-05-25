package com.mp.ttapi.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;
import com.mp.ttapi.domain.UserKey;

@ComponentScan(basePackages = { "com.mp.ttapi.config" })
@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        
        //tesla db
//        ds.setUrl("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net/heroku_bab273b5d316bf5");
//        ds.setUsername("bc27b6a0eb9c42");
//        ds.setPassword("969dd089");
//        return ds;

        //mizuno-golf
        ds.setUrl("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net/heroku_49ffdd17f0afee0");
        ds.setUsername("b83af1e37b42fe");
        ds.setPassword("44f0184f");
       return ds; 
      //zen-pencils DB
//      ds.setUrl("jdbc:mysql://@us-cdbr-iron-east-04.cleardb.net/heroku_d694844b87d61ea");
//      ds.setUsername("bfaf834bd27376");
//      ds.setPassword("6a241d46");
//      return ds;
        
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
        sessionBuilder.addAnnotatedClasses(ImageChecksum.class, FileTranslation.class, ImageTranscription.class, ImageTranslation.class, UserKey.class);
        sessionBuilder.addProperties(getHibernateProperties());
       return sessionBuilder.buildSessionFactory();
    }
    
    public Properties getHibernateProperties(){
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.show_sql", "false");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        return props;
    }
    
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager tm = new HibernateTransactionManager(sessionFactory);
        return tm;
    }
    
    @Bean
    protected CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver mpr = new CommonsMultipartResolver();
        mpr.setMaxUploadSize(10240000);
        return mpr;
    }
}