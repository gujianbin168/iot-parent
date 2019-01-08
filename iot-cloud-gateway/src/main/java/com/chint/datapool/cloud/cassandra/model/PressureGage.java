//package com.chint.datapool.cloud.cassandra.model;
//
//import java.util.Date;
//import java.util.UUID;
//import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import org.springframework.data.cassandra.core.mapping.Table;
//
//
////压力表
//@Table
//public class PressureGage implements Sensor {
//	@PrimaryKey
//	private UUID id;
//
//	private String companyName;
//	
//	private String deviceName;
//	
//	private String description;
//		
//	private Date registerTime;
//
//	
//	public PressureGage() {
//	}
//
//	public PressureGage(UUID id, String companyName, String deviceName, String description, Date registerTime) {
//		this.id = id;
//		this.companyName = companyName;
//		this.deviceName = deviceName;
//		this.description = description;
//		this.registerTime = registerTime;
//	}
//
//	@Override
//	public String toString() {
//		return String.format("PressuraGage[id=%s, companyName='%s', deviceName='%s', description='%s', registerTime='%s']", 
//				this.id,this.companyName, this.deviceName,this.description, this.registerTime);
//	}
//
//	public UUID getId() {
//		return id;
//	}
//
//	public void setId(UUID id) {
//		this.id = id;
//	}
//
//	public String getCompanyName() {
//		return companyName;
//	}
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
//
//	public String getDeviceName() {
//		return deviceName;
//	}
//
//	public void setDeviceName(String deviceName) {
//		this.deviceName = deviceName;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Date getRegisterTime() {
//		return registerTime;
//	}
//
//	public void setRegisterTime(Date registerTime) {
//		this.registerTime = registerTime;
//	}
//}
