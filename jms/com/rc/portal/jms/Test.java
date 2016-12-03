package com.rc.portal.jms;

import javax.jms.JMSException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rc.commons.jms.MessageModel;


/**
 * @Title: Test.java
 * @Description:
 * @author yinbinhome@163.com
 * @date 2012-1-30 下午04:08:54
 * @version V1.0
 */

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws JMSException, Exception {
		System.out.println("初始化spring！准备开始接收！");
		 ApplicationContext context = new
		 ClassPathXmlApplicationContext("applicationContext-send.xml");
		 MessageSender	 dd=(MessageSender)context.getBean("topicMessageSender");
		 dd.sendMessage(new MessageModel("v2","dfasdf"));
		 Thread.sleep(2000);
		 dd.sendMessage(new MessageModel("v2","dfa1111sdf"));


	}

}
