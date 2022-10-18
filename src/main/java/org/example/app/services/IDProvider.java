package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.apache.log4j.Logger;

public class IDProvider implements InitializingBean, DisposableBean, BeanPostProcessor {

    Logger logger = Logger.getLogger(IDProvider.class);

    public String provideId(Book book) {
        return this.hashCode() + "_" + book.hashCode();
    }

    @Override
    public void destroy() throws Exception {}

    @Override
    public void afterPropertiesSet() throws Exception {}
}
