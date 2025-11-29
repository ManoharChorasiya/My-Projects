package com.info.jpatest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="student_name")
public class StudentCourse {
	
	@Column(name="student_name")
	private String studentName;
	@Column(name="student_course")
	private String studentCourse;
	
	@EmbeddedId
	private StudentCourseKey key;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}

	public StudentCourseKey getKey() {
		return key;
	}

	public void setKey(StudentCourseKey key) {
		this.key = key;
	}

	public StudentCourse() {
		// TODO Auto-generated constructor stub
	}

}
