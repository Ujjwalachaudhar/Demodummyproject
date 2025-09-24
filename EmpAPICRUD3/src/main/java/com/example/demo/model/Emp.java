package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee")
public class Emp 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long empid;
    private String empname;
    private String empemailid;
    private double empsalary;
    private String empphoneno;
    private String emppassword;
    
    public Emp() {}
	public Emp(Long empid, String empname, String empemailid, double empsalary, String empphoneno, String emppassword) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.empemailid = empemailid;
		this.empsalary = empsalary;
		this.empphoneno = empphoneno;
		this.emppassword = emppassword;
	}
	@Override
	public String toString() {
		return "Emp [empid=" + empid + ", empname=" + empname + ", empemailid=" + empemailid + ", empsalary="
				+ empsalary + ", empphoneno=" + empphoneno + ", emppassword=" + emppassword + "]";
	}
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpemailid() {
		return empemailid;
	}
	public void setEmpemailid(String empemailid) {
		this.empemailid = empemailid;
	}
	public double getEmpsalary() {
		return empsalary;
	}
	public void setEmpsalary(double empsalary) {
		this.empsalary = empsalary;
	}
	public String getEmpphoneno() {
		return empphoneno;
	}
	public void setEmpphoneno(String empphoneno) {
		this.empphoneno = empphoneno;
	}
	public String getEmppassword() {
		return emppassword;
	}
	public void setEmppassword(String emppassword) {
		this.emppassword = emppassword;
	}
	
	
}
