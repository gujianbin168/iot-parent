package com.chint.datapool.cloud.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chint.datapool.cloud.common.base.BaseResponse;
import com.chint.datapool.cloud.entity.Student;
import com.chint.datapool.cloud.service.StudentService;

@Api(value="/student", tags="学生接口模块")
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	private static Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@ApiOperation(value="删除用户", notes="删除用户信息")
	@RequestMapping("/deleteById/{id}")
	public BaseResponse deleteById(@PathVariable Integer id) {
		BaseResponse result = new BaseResponse();
		studentService.deleteById(id);
		result.setMsg("删除成功");
		logger.info("删除成功");
		return result;
	}

	//@ApiOperation(value = "保存学生", notes = "保存学生")
	@RequestMapping("/saveStudent")
  //@PostMapping(value = "/saveStudent", produces = MediaType.APPLICATION_JSON_VALUE)
	//public BaseResponse<Student> saveStudent(@RequestBody Student student) {
	public BaseResponse<Student> saveStudent() {
	//public BaseResponse saveStudent() {
		BaseResponse result = new BaseResponse();
//		logger.info("city:"+student.getCity());
//		logger.info("name:"+student.getName());
//		logger.info("school:"+student.getSchool());
		Student student = new Student();
//		student.setId(4);
		student.setCity("北京5");
		student.setName("李四5");
		student.setSchool("北大5");
		studentService.insertStudent(student);
		result.setMsg("保存成功");
		logger.info("保存成功");
		return result;
	}

	@RequestMapping("/updateByprimaryKey")
	public BaseResponse updateByprimaryKey() {
	//public BaseResponse updateByprimaryKey(@RequestBody Student student) {
		BaseResponse result = new BaseResponse();
		EntityWrapper<Student> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("name", "4");
		//entityWrapper.eq("name", student.getName());
		Student stu = studentService.selectOne(entityWrapper);
		if (null != stu) {
			stu.setCity("city4");
			stu.setSchool("school4");
		}
		studentService.updateByprimaryKey(stu);
		result.setMsg("更新成功");
		logger.info("更新成功");
		return result;
	}
	
	@RequestMapping("/findSomeColumn")
	public BaseResponse findSomeColumn() {
		BaseResponse result = new BaseResponse();
		List<Student> stu = studentService.findSomeColumn();
		if(stu != null){
			for(int i=0;i<stu.size();i++){
				Student student = stu.get(i);
				logger.info("学生"+"i:"+student.getId()+","+student.getName()+","+student.getSchool()+","+student.getCity());
			}
		}
		result.setData(stu);
		logger.info("findSomeColumn 成功");
		return result;
	}

	/**
	 * 分页的方法
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("page/{pageNumber}")
	public BaseResponse findAllStuPage(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "6") Integer pageSize) {
		BaseResponse result = new BaseResponse();
		Page page = new Page(pageNumber, pageSize);
		Page<Student> pageStu = studentService.findAllStudentPage(page);
		if(pageStu != null){
			List<Student> studentList = pageStu.getRecords();
			for(int i=0;i<studentList.size();i++){
				Student student = studentList.get(i);
				logger.info("学生"+"i:"+student.getId()+","+student.getName()+","+student.getSchool()+","+student.getCity());
			}
		}
		result.setData(pageStu.getRecords());
		return result;
	}
	
//	@RequestMapping("/register")
// 	public BaseResponse Register(@RequestBody Student student) {
//		studentService.insert(student);
//		return new BaseResponse(Constants.ResultCode.SUCCESS, "保存成功");
//	}

//	@RequestMapping("/findAllStudent")
//	public BaseResponse findAllStudent() {
//		BaseResponse result = new BaseResponse();
//		List<Student> student = studentService.findAllStudent();
//		result.setData(student);
//		return result;
//	}

 



	@RequestMapping("pageByGender/{pageNumber}")
	public BaseResponse findStuByGender(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "6") Integer pageSize) {
		BaseResponse result = new BaseResponse<>();
		EntityWrapper<Student> wrapper = new EntityWrapper<>();
		wrapper.eq("gender", 1);
		Page<Student> page = getPage(pageNumber, pageSize);
		Page<Student> stuPage = studentService.selectPage(page, wrapper);
		result.setData(stuPage.getRecords());
		return result;
	}

	/**
	 * 获取分页对象 每页显示数量
	 */
	private <T> Page<T> getPage(int pageNum, int pageSize) {
		return new Page<T>(pageNum, pageSize);
	}
}
