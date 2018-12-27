package com.chint.datapool.cloud.response;

import java.util.List;
import com.chint.datapool.cloud.common.base.BaseResponse;
import com.chint.datapool.cloud.dto.StudentDTO;

public class StudentResponse extends BaseResponse{
	private List<StudentDTO> studentEntityList;

	public List<StudentDTO> getStudentEntityList() {
		return studentEntityList;
	}

	public void setStudentEntityList(List<StudentDTO> studentEntityList) {
		this.studentEntityList = studentEntityList;
	}
	
}
