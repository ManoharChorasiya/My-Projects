package com.info.jpatest.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Embeddable
public class StudentCourseKey {
	@Column(name="student_id")
	private int studentId;
	@Column(name="course_id")
	private int courseId;
	
	@Override
	public int hashCode() {
		return Objects.hash(studentId,courseId);
	}
//	public boolean equals(Object obj) {
//		if(this == obj) return true;
//	}
	public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof StudentCourseKey)) return false;
        
        StudentCourseKey sc = (StudentCourseKey) obj;
		return this.studentId == sc.studentId && this.courseId == sc.courseId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public StudentCourseKey() {
		// TODO Auto-generated constructor stub
	}

}
