package com.chint.datapool.cloud.safe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.HtmlUtils;

/**
 * 描述 : 跨站请求防范
 *
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * 描述 : 构造函数
	 *
	 * @param request
	 *            请求对象
	 */
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if(value!=null)
			return HtmlUtils.htmlEscape(value);
		else
			return "";
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value!=null){
			return HtmlUtils.htmlEscape(value);
		}else
			return ""; 
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = HtmlUtils.htmlEscape(values[i]);
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}

}