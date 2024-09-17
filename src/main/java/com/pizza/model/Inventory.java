package com.pizza.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String itemname;
	private Long itemqty;
	private Long lowlevel;
	private String measurement;
	private Long refillLevel;
	private String isRefillSelected;
	
	@Lob
	@Column(length = 104857600)
	private byte[] itemimg;
	
	public Inventory() {
		super();
	}

	public Inventory(Long id, String itemname, Long itemqty, Long lowlevel, String measurement, Long refillLevel,
			String isRefillSelected, byte[] itemimg) {
		super();
		this.id = id;
		this.itemname = itemname;
		this.itemqty = itemqty;
		this.lowlevel = lowlevel;
		this.measurement = measurement;
		this.refillLevel = refillLevel;
		this.isRefillSelected = isRefillSelected;
		this.itemimg = itemimg;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", itemname=" + itemname + ", itemqty=" + itemqty + ", lowlevel=" + lowlevel
				+ ", measurement=" + measurement + ", refillLevel=" + refillLevel + ", isRefillSelected="
				+ isRefillSelected + ", itemimg=" + Arrays.toString(itemimg) + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Long getItemqty() {
		return itemqty;
	}

	public void setItemqty(Long itemqty) {
		this.itemqty = itemqty;
	}

	public Long getLowlevel() {
		return lowlevel;
	}

	public void setLowlevel(Long lowlevel) {
		this.lowlevel = lowlevel;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public Long getRefillLevel() {
		return refillLevel;
	}

	public void setRefillLevel(Long refillLevel) {
		this.refillLevel = refillLevel;
	}

	public String getIsRefillSelected() {
		return isRefillSelected;
	}

	public void setIsRefillSelected(String isRefillSelected) {
		this.isRefillSelected = isRefillSelected;
	}

	public byte[] getItemimg() {
		return itemimg;
	}

	public void setItemimg(byte[] itemimg) {
		this.itemimg = itemimg;
	}

}
