package com.chint.datapool.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_student")  
public class Student {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 2932192902254426130L;
 
		//对应id，可不填
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
 
    //对应字段名，如果属性名和字段名一致，可不填
    @TableField("name")
    private String name;
 
    private String school;
 
    private String city;
 
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSchool() {
			return school;
		}

		public void setSchool(String school) {
			this.school = school;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

}
