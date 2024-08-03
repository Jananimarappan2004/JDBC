package com.jdbc;

import java.sql.Date;

public class Employee {
	int id;
	String name;
	float Salary;
	String dept;
	String designation;
	Date doj;
	Date dob;
	byte[] photo;
	String address;

	public Employee(int id, String name, float salary, String dept, String designation, Date doj, Date dob,
			byte[] photo, String address) {
		super();
		this.id = id;
		this.name = name;
		Salary = salary;
		this.dept = dept;
		this.designation = designation;
		this.doj = doj;
		this.dob = dob;
		this.photo = photo;
		this.address = address;
	}
	
	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSalary() {
		return Salary;
	}
	public void setSalary(float salary) {
		Salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
