package com.chint.datapool.cloud.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chint.datapool.cloud.dao.StudentMapper;
import com.chint.datapool.cloud.entity.Student;
import com.chint.datapool.cloud.service.StudentService;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService{
	
	@Autowired
	private StudentMapper studentDao;	
 
	private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
  @Override
  public List<Student> findAllStudent() {
      // TODO Auto-generated method stub
      return baseMapper.findAllStudent();
  }

  @Override
  public List<Student> findSomeColumn() {
      // TODO Auto-generated method stub
      return baseMapper.findSomeColumn();
  }

  @Override
  public void deleteById(Integer id) {
  	baseMapper.deleteById(id);

  }

  @Override
  public void updateByprimaryKey(Student student) {
  	baseMapper.updateByprimaryKey(student);
  }
  
  
  @Override
  public void insertStudent(Student student) {
  	baseMapper.insertStudent(student);
  }
  
  @Override
//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
  public void saveStudent(Student student) {
      //baseMapper.saveStudent(student);
      baseMapper.saveStudent(student);
  }

  @Override
  public Page<Student> findAllStudentPage(Page<Student> page) {
      // TODO Auto-generated method stub
      page.setRecords(baseMapper.findAllStudentPage(page));
      return page;
  }

  @Override
  public List<Student> findStuByGender(Integer gender) {
      // TODO Auto-generated method stub
      return baseMapper.findStuById(gender);
  }
 
 	
//	@Override
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
//	public BaseResponse delStudent(StudentRequest qo) throws ChintAplException{
//		logger.info("enter delCarModelInfo with param="+JsonUtil.toJSONString(qo));
//		BaseResponse vo = new BaseResponse();
//		Integer studentId = qo.getId();
//		baseMapper.deleteById(studentId);
//		return vo;		
//	}
 
}
