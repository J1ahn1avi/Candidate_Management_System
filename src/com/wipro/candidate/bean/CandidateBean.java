package com.wipro.candidate.bean;

import java.util.Objects;

public class CandidateBean {

	private String id;
	private String name;
	private int m1;
	private int m2;
	private int m3;
	private String result;
	private String grade;
	public CandidateBean(String id, String name, int m1, int m2, int m3, String result, String grade) {
		super();
		this.id = id;
		this.name = name;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.result = result;
		this.grade = grade;
	}
	public CandidateBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CandidateBean [id=" + id + ", name=" + name + ", m1=" + m1 + ", m2=" + m2 + ", m3=" + m3 + ", result="
				+ result + ", grade=" + grade + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(grade, id, m1, m2, m3, name, result);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateBean other = (CandidateBean) obj;
		return Objects.equals(grade, other.grade) && Objects.equals(id, other.id) && m1 == other.m1 && m2 == other.m2
				&& m3 == other.m3 && Objects.equals(name, other.name) && Objects.equals(result, other.result);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getM1() {
		return m1;
	}
	public void setM1(int m1) {
		this.m1 = m1;
	}
	public int getM2() {
		return m2;
	}
	public void setM2(int m2) {
		this.m2 = m2;
	}
	public int getM3() {
		return m3;
	}
	public void setM3(int m3) {
		this.m3 = m3;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

}
