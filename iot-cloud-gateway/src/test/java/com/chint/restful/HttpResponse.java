package com.chint.restful;

import org.apache.http.Header;

public class HttpResponse {

	private String body;
	private Header[] headers;
	private String reasonPhrase;
	private Integer statusCode;
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getReasonPhrase() {
		return reasonPhrase;
	}
	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
