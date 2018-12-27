package com.chint.datapool.cloud.common.util;

import org.springframework.beans.BeanUtils;

import java.util.Iterator;
import java.util.List;

public class ListUtil {

	/**
	 * List对象COPY
	 * @param source
	 * @param target
	 * @param targetClazz
	 * @param <T>
	 * @param <M>
	 */
	public static <T,M> void copyList(List<T> source, List<M> target, Class<M> targetClazz) {
		
		Iterator<T> itr = source.iterator(); 
        while(itr.hasNext()){
        	T sr = itr.next();
        	M obj = null;
        	try {
				obj = targetClazz.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	BeanUtils.copyProperties(sr, obj);
        	target.add(obj);
        } 
	}
}
