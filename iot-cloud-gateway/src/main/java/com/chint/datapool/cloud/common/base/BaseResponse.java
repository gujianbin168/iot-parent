package com.chint.datapool.cloud.common.base;

import com.chint.datapool.cloud.common.constant.Constants.ResultCode;

public class BaseResponse<T> {
	private String code;
	private String msg;
	private T data;

	public BaseResponse() {
		this.code = ResultCode.SUCCESS;
		this.msg = "success";
	}

	public BaseResponse(Integer code, String msg) {
		super();
		this.code = ResultCode.SUCCESS;
		this.msg = msg;
	}

	public BaseResponse(String msg, T data) {
		super();
		this.msg = msg;
		this.data = data;
	}

	public BaseResponse(Integer code, String msg, T data) {
		super();
		this.code = ResultCode.SUCCESS;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
