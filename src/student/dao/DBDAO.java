package student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import student.entity.Student;
import util.SqlHelper;


public class DBDAO {
	SqlHelper sqlhelper = new SqlHelper();
	
	public ArrayList<Student> checkStudent(Student student){
		String sql = "select * from T_student where studentID=? and password=?";
		
		String[] parameters={student.getStudentID(),student.getPassword()};
		System.out.println(parameters);
		ArrayList<Student> list = sqlhelper.executeQuery(sql, parameters);
		return list;
		/*if(list.size() == 1){
			System.out.println("≤È—Ø≥…π¶");
			return list;
		}else{
			return list;
		}*/
		
	}
	
}
