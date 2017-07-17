package com.huagege.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	private String id;
	private String nickname;
	private String name;
	private String sex;
	private String birthday;
	private String place;
	private String dept;
	private String clazz;
	private String phoneNum;
	private String idCard;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Student(String id, String nickname, String name, String sex,
			String birthday, String place, String dept, String clazz,
			String phoneNum, String idCard) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.place = place;
		this.dept = dept;
		this.clazz = clazz;
		this.phoneNum = phoneNum;
		this.idCard = idCard;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", nickname=" + nickname + ", name="
				+ name + ", sex=" + sex + ", birthday=" + birthday + ", place="
				+ place + ", dept=" + dept + ", clazz=" + clazz + ", phoneNum="
				+ phoneNum + ", idCard=" + idCard + "]";
	}
	
}
