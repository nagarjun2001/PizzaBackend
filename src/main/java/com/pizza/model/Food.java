package com.pizza.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fname;
	private String fdesc;
	private double fprice;
	private String ftype;
	
	@Lob
	@Column(length = 104857600)
	private byte[] fimg;
	
	public Food() {
		super();
	}

	public Food(Long id, String fname, String fdesc, double fprice, String ftype, byte[] fimg) {
		super();
		this.id = id;
		this.fname = fname;
		this.fdesc = fdesc;
		this.fprice = fprice;
		this.ftype = ftype;
		this.fimg = fimg;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", fname=" + fname + ", fdesc=" + fdesc + ", fprice=" + fprice + ", ftype=" + ftype
				+ ", fimg=" + Arrays.toString(fimg) + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	public double getFprice() {
		return fprice;
	}

	public void setFprice(double fprice) {
		this.fprice = fprice;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public byte[] getFimg() {
		return fimg;
	}

	public void setFimg(byte[] fimg) {
		this.fimg = fimg;
	}

}
