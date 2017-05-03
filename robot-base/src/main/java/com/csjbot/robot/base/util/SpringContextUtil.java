package com.csjbot.robot.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    public Object getBean(String id) {
        return this.applicationContext.getBean(id);
    }

    public Object getBean(Class<?> c) {
        return this.applicationContext.getBean(c);
    }
}
