package student.dao;

import java.util.ArrayList;

import student.entity.Student;
import util.SqlHelper;


public class DBDAO {
	SqlHelper sqlhelper = new SqlHelper();
	
	public ArrayList<Student> checkStudent(Student student){
		String sql = "select * from T_student where studentID=? and password=?";
		
		String[] parameters={student.getStudentID(),student.getPassword()};
		//System.out.println(parameters);
		ArrayList<Object> list = sqlhelper.executeQuery(sql, parameters);
		
		//这里返回的Arraylist是Object类的，我们需要把他转换成Student类
		ArrayList<Student> students = new ArrayList<Student>();
		
		for (int i = 0; i < list.size(); i++) {
			Student stu = new Student();
			Object[] obj = (Object[])list.get(i);
			stu.setStudentID(obj[0].toString());
			stu.setStudentName(obj[1].toString());
			stu.setMajorID(obj[2].toString());
			stu.setPassword(obj[3].toString());
			students.add(stu);
		}
		
		return students;
		
	}
	
}
