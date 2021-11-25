package com.geek.spring.jms;

import com.geek.spring.bean.Student;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/20
 */

@Component
public class SendService {

//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public void send(final Student stu) {
//        jmsTemplate.send("test.queue", new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(JSON.toJSONString(stu));
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        Student student = new Student();
//        student.setName("student004");
//        student.setId(4l);
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springjms-sender.xml");
//        SendService sendService = (SendService) context.getBean("sendService");
//        sendService.send(student);
//    }
}
