package com.chint.datapool.cloud.common.entity;


//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImageParam {
	private String fileKey;
	
	private String name;

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}