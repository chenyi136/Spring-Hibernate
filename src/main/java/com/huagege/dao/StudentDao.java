package com.huagege.dao;

import com.huagege.domain.Student;

public interface  StudentDao {
	public void add(Student student);
	public void delete(Student student);
	public void update(Student student);
	public Student find(String id);
	
}
