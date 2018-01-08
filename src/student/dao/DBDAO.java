package student.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;

import student.entity.Student;
import student.entity.CourseInfo;
import util.DBUtil;
import util.SqlHelper;


public class DBDAO {
	SqlHelper sqlhelper = new SqlHelper();
	
	//登录检查用户名密码
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
	
	//获取课程粗略信息
	public ArrayList<CourseInfo> CourseInfo(CourseInfo infoin){
		String sql = "select courseID,courseName,teacherName from courseinfo where courseName=?";
		
		String[] parameters={infoin.getCourseName()};
		//System.out.println(parameters);
		ArrayList<Object> list = sqlhelper.executeQuery(sql, parameters);
		
		//这里返回的Arraylist是Object类的，我们需要把他转换成Student类
		ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();
		
		for (int i = 0; i < list.size(); i++) {
			CourseInfo infoout = new CourseInfo();
			Object[] obj = (Object[])list.get(i);
			infoout.setCourseID(obj[0].toString());
			infoout.setCourseName(obj[1].toString());
			infoout.setTeacherName(obj[2].toString());
			infos.add(infoout);
		}	
		return infos;
		
	}
	
	//获取课程详细信息
	public ArrayList<CourseInfo> CourseDetail (CourseInfo infoin){
		String sql = "select * from courseinfo where courseID=?";
		
		String[] parameters={infoin.getCourseID()};
		//System.out.println(infoin.getCourseID());
		ArrayList<Object> list = sqlhelper.executeQuery(sql, parameters);
		
		//这里返回的Arraylist是Object类的，我们需要把他转换成Student类
		ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();
		
		for (int i = 0; i < list.size(); i++) {
			CourseInfo infoout = new CourseInfo();
			Object[] obj = (Object[])list.get(i);
			infoout.setCourseID(obj[0].toString());
			infoout.setCourseName(obj[1].toString());
			infoout.setStarttime(obj[2].toString());
			infoout.setEndtime(obj[3].toString());
			infoout.setWeekTime(obj[4].toString());
			infoout.setDayTime(obj[5].toString());
			infoout.setTeacherName(obj[6].toString());
			infoout.setClassroomName(obj[7].toString());
			infoout.setMajorName(obj[8].toString());
			infoout.setDepartmentName(obj[9].toString());
			infos.add(infoout);
		}	
		return infos;
		
	}
	
	//获取全部的课程的粗略信息
	public ArrayList<CourseInfo> ShowAllCourses(){
		String sql = "select courseID,courseName,teacherName from courseinfo ";
		
		String[] parameters={};
		//System.out.println(parameters);
		ArrayList<Object> list = sqlhelper.executeQuery(sql,parameters);
		
		//这里返回的Arraylist是Object类的，我们需要把他转换成Student类
		ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();
		
		for (int i = 0; i < list.size(); i++) {
			CourseInfo infoout = new CourseInfo();
			Object[] obj = (Object[])list.get(i);
			infoout.setCourseID(obj[0].toString());
			infoout.setCourseName(obj[1].toString());
			infoout.setTeacherName(obj[2].toString());
			infos.add(infoout);
		}	
		return infos;
		
	}
	
	//获取学生课程表
	/*public ArrayList<CourseInfo> CourseTable(Student studentin) throws Exception{
		//应为调用有返回值的存储过程，工具类中没有这种的函数，所以这里另外写的
		
		Connection con = DBUtil.getConnection();
		CallableStatement cstmt = con.prepareCall("{call dbo.stu_coursetable(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
	     cstmt.setString(1, studentin.getStudentID());
	     cstmt.registerOutParameter(2, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(3, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(4, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(5, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(6, java.sql.Types.CHAR);
	     cstmt.registerOutParameter(7, java.sql.Types.CHAR);
	     cstmt.execute();
		
		//ArrayList<Object> list = cstmt.registerOutParameter(2, java.sql.Types.INTEGER);;
		
		//这里返回的Arraylist是Object类的，我们需要把他转换成Student类
		ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();
		
		for (int i = 0; i < list.size(); i++) {
			CourseInfo infoout = new CourseInfo();
			Object[] obj = (Object[])list.get(i);
			infoout.setCourseID(obj[0].toString());
			infoout.setCourseName(obj[1].toString());
			infoout.setTeacherName(obj[2].toString());
			infos.add(infoout);
		}	
		return infos;
		
	}*/
	
}
