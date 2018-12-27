package com.chint.datapool.cloud.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LableValue {
	public LableValue(){
		super();
	}

	/** 显示项目 */
	private String label;
	/** 值 */
	private String value;
	
	public LableValue(String key, String value){
		super();
		this.value = key;
		this.label = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
