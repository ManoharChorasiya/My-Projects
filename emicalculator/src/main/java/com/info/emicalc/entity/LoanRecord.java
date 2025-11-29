package com.info.emicalc.entity;

import java.sql.Timestamp;

public class LoanRecord {
	private int id;
	private int userId;
	private double principal;
	private double annualRate;
	private int tenureMonths;
	private double emi;
	private double totalPayment;
	private double totalInterest;
	private Timestamp createdAt;

	public LoanRecord() {
	}

	public LoanRecord(int id, int userId, double principal, double annualRate, int tenureMonths, double emi,
			double totalPayment, double totalInterest, Timestamp createdAt) {
		this.id = id;
		this.userId = userId;
		this.principal = principal;
		this.annualRate = annualRate;
		this.tenureMonths = tenureMonths;
		this.emi = emi;
		this.totalPayment = totalPayment;
		this.totalInterest = totalInterest;
		this.createdAt = createdAt;
	}

	public LoanRecord(int userId, double principal, double annualRate, int tenureMonths, double emi,
			double totalPayment, double totalInterest) {
		this.userId = userId;
		this.principal = principal;
		this.annualRate = annualRate;
		this.tenureMonths = tenureMonths;
		this.emi = emi;
		this.totalPayment = totalPayment;
		this.totalInterest = totalInterest;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(double annualRate) {
		this.annualRate = annualRate;
	}

	public int getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public double getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
