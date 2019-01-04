package com.chint.datapool.cloud.dto;

import java.io.Serializable;

 

public class StudentDTO implements Serializable {
 
  /**
	 * 
	 */
	private static final long serialVersionUID = -5502836374168079805L;

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
