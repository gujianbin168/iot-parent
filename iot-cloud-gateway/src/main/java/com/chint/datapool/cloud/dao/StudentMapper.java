package com.chint.datapool.cloud.dao;

import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.chint.datapool.cloud.entity.Student;


@Repository
public interface StudentMapper extends BaseMapper<Student> {
  List<Student> findAllStudent();

  List<Student> findSomeColumn();

  void deleteById(Integer id);

  void updateByprimaryKey(Student student);
  
  void insertStudent(Student student);
  
  void saveStudent(Student student);

  List<Student> findAllStudentPage(Pagination page);


  @Select("select * from tb_student where id = #{id}")
  @Results({
      @Result(column="id",property="id"),
      @Result(column="name",property="name"),
      @Result(column="city",property="city"),
      @Result(column="school",property="school")
  })
  List<Student> findStuById(Integer gender);
 
}
