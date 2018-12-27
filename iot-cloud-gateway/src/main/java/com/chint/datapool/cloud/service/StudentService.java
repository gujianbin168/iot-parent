package com.chint.datapool.cloud.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.chint.datapool.cloud.entity.Student;


public interface StudentService extends IService<Student>{
 
  List<Student> findAllStudent();
  
  List<Student> findSomeColumn();
  
  void insertStudent(Student student);
  
  void deleteById(Integer id);

  void updateByprimaryKey(Student student);

  void saveStudent(Student student);

  Page<Student> findAllStudentPage(Page<Student> page);

  List<Student> findStuByGender(Integer gender);
 
}
