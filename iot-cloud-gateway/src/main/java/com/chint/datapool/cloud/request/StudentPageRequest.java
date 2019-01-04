package com.chint.datapool.cloud.request;

import com.chint.datapool.cloud.common.base.BaseRequest;

public class StudentPageRequest extends BaseRequest{
 
  /**
	 * 
	 */
	private static final long serialVersionUID = -4080767644603422440L;

	private int id;
 
  private String name;

  private String school;

  private String city;
 	
	int page;
	int pagesize;
 	
	
	public int getAllstart() {
		return (page-1)*pagesize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
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
