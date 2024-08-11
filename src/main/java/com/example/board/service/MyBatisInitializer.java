package com.example.board.service;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisInitializer implements ServletContextListener {
    static SqlSessionFactory sqlSessionFactory;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sce.getServletContext().setAttribute("sqlSessionFactory", sqlSessionFactory);
            System.out.println("sqlSessionFactory 시작");

        } catch (IOException e) {
            throw new RuntimeException("Error initializing MyBatis", e);
        }
    }
    static SqlSession getSession() {
        return sqlSessionFactory.openSession(true);
    }
}
