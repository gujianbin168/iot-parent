package com.chint.datapool.cloud.request;

import com.chint.datapool.cloud.common.base.BaseRequest;

public class StudentRequest extends BaseRequest{
	

  /**
	 * 
	 */
	private static final long serialVersionUID = 681917853181121700L;

	private int id;

  private String name;

  private String school;

  private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
