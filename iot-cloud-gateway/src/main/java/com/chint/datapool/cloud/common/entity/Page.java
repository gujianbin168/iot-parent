package com.chint.datapool.cloud.common.entity;

public class Page {
	/** 页码，默认是第一页 */
	private int pageNo = 1;

	/** 每页显示的记录数，默认是10 */
	private int pageSize = 50;

	/** 总记录数 */
	private int totalRecord;

	/** 总页数 */
	private int totalPage;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
