package com.huagege.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.huagege.domain.Student;

public class StudentDaoImpl implements StudentDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(Student student) {
		// TODO Auto-generated method stub

	}

	public void delete(Student student) {
		// TODO Auto-generated method stub

	}

	public void update(Student student) {
		// TODO Auto-generated method stub

	}

	public Student find(String id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Student student = (Student)session.get(Student.class, id);
		session.getTransaction().commit();
		session.close();
		return student;
	}

}
