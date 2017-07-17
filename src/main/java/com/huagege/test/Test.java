package com.huagege.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huagege.dao.StudentDao;
import com.huagege.domain.Student;

public class Test {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		StudentDao studentDao = (StudentDao)applicationContext.getBean("studentDao");
		Student student = studentDao.find("20141737");
		System.out.println(student);
	}

}
