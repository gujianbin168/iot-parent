package com.chint.datapool.cloud.safe;

import java.io.IOException;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 描述 : 基于xss的JsonSerializer
 *
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

	@Override
	public Class<String> handledType() {
		return String.class;
	}

	@Override
	public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		if (value != null) {
			if(value.contains("q-sign-algorithm")){ //cos--签名问题
				jsonGenerator.writeString(value);
			}else{
				String encodedValue = HtmlUtils.htmlEscape(value);
				jsonGenerator.writeString(encodedValue);				
			}
		}
	}

}
