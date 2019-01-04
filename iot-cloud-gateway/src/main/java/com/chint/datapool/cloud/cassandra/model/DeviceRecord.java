package com.chint.datapool.cloud.cassandra.model;

import java.util.Date;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


//设备历史记录
@Table
public class DeviceRecord   {
	@PrimaryKey
	private UUID id;

	private UUID deviceId;
	
	private Date uploadTime;

	private String property;

	public DeviceRecord() {
	}

	public DeviceRecord(UUID id, UUID deviceId, Date uploadTime, String property) {
		this.id = id;
		this.deviceId = deviceId;
		this.uploadTime = uploadTime;
		this.property = property;
	}

	@Override
	public String toString() {
		return String.format("DeviceRecord[id=%s, deviceId=%s, uploadTime='%s', property='%s']", 
				this.id,this.deviceId,this.uploadTime, this.property);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(UUID deviceId) {
		this.deviceId = deviceId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
