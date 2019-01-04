package com.chint.datapool.cloud.response;

import java.util.List;
import com.chint.datapool.cloud.common.base.BaseResponse;

public class StudentPageResponse<T> extends BaseResponse{
	private List<T> T;
	
	private T entity;

	/**总条数*/
	private Long totalNum=0l;
	/**分销车源总条数*/
	private Long carDisNum=0L;
	/**普通车源总条数*/
	private Long generalCarsourceNum=0L;
	
	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public List<T> getT() {
		return T;
	}

	public void setT(List<T> t) {
		T = t;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public Long getCarDisNum() {
		return carDisNum;
	}

	public void setCarDisNum(Long carDisNum) {
		this.carDisNum = carDisNum;
	}

	public Long getGeneralCarsourceNum() {
		return generalCarsourceNum;
	}

	public void setGeneralCarsourceNum(Long generalCarsourceNum) {
		this.generalCarsourceNum = generalCarsourceNum;
	}
	
	
}
