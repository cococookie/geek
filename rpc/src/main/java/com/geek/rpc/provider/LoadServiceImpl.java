package com.geek.rpc.provider;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/18
 */

@Service
public class LoadServiceImpl  implements LoadService,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object load(String serviceClass) {
        String[] strs = applicationContext.getBeanDefinitionNames();
        System.out.println(strs);
        return applicationContext.getBean(serviceClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
