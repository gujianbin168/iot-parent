//package com.chint.datapool.cloud.cassandra.controller;
//
//import java.util.UUID;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.chint.datapool.cloud.cassandra.model.Device;
//import com.chint.datapool.cloud.cassandra.model.PressureGage;
//import com.chint.datapool.cloud.cassandra.service.PressureGageService;
//import com.chint.datapool.cloud.cassandra.util.BaseResponse;
//import com.chint.datapool.cloud.cassandra.util.Constants;
//
//
// 
//@RestController
//public class PressureGageController {
//	@Autowired
//	private PressureGageService pressureGageService;
//	
//
//	
//	@PostMapping(value = "/savePressureGage/{company}.json", produces = MediaType.APPLICATION_JSON_VALUE)
//	public BaseResponse<Device> saveStudent(@PathVariable String company, @RequestBody PressureGage pressureGage) {
//		BaseResponse result = new BaseResponse();
//		if(company == null){//company 为各个产业公司名
//			result.setCode("parameters error");//?????
//			return result;
//		}
//		company = company.toLowerCase();
//		System.out.println(company);
//		if(company.equals(Constants.COMPANY_LCJS)){//量测技术研究院
//			pressureGageService.save(pressureGage);
//			result.setMsg("保存成功");
//			System.out.println(pressureGage);
//		}else if(company.equals(Constants.COMPANY_TJS)){//泰杰赛智能科技公司
//			
//		}else if(company.equals(Constants.COMPANY_XH)){//上海新华研发中心
//			
//		}else if(company.equals(Constants.COMPANY_ZZ)){//正泰中自研发中心
//			
//		}else if(company.equals(Constants.COMPANY_XNY)){//新能源电站运维事业部
//			
//		} 
//		return result;
//	}
//	
// 	@RequestMapping("/deletePressureGage/{id}.json")
//	public BaseResponse deletePressureGage(@PathVariable UUID id) {
// 		PressureGage pressureGage = pressureGageService.findByID(id);
//		System.out.println(pressureGage);
//		pressureGageService.deleteByID(id);
//		BaseResponse result = new BaseResponse();
//		result.setMsg("删除成功");
//		return result;
//	}
//
//	@PostMapping(value = "/updatePressureGage", produces = MediaType.APPLICATION_JSON_VALUE)
//	public BaseResponse<Device> updateStudent(@RequestBody PressureGage pressureGage) {	
//		System.out.println(pressureGage);
//		PressureGage dbPressureGage = pressureGageService.findByID(pressureGage.getId());
//		BeanUtils.copyProperties(pressureGage, dbPressureGage);
//		pressureGageService.update(dbPressureGage);
//		System.out.println(dbPressureGage);
//		BaseResponse result = new BaseResponse();
//		result.setMsg("更新成功");
//		return result;
//	}
//}
