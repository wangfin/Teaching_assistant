package course.dao;

import java.util.ArrayList;

import course.entity.Course;
import student.entity.Student;
import util.SqlHelper;

public class CourseDao {
	SqlHelper sqlhelper = new SqlHelper();
	
	public void insertCourse(Course course) {
		String sql = "EXEC dbo.CourseInsertProc "
				+ "@courseID = ?, @courseName = ?,"
				+ "@teacherID = ?, @classroomID = ?,"
				+ "@starttime = ?, @endtime = ?";
		
		String[] parameters={course.getCourseID(), course.getCourseName(),
				course.getTeacherID(), course.getClassroomID(),
				course.getStarttime(), course.getEndtime()};
		System.out.println(parameters);
		sqlhelper.executeQuery(sql, parameters);
		
	}
	
	public ArrayList<Course> selectSingleCourse(String courseID){
		String sql = "EXEC dbo.CourseSelectProc @courseID = ?";
		
		String[] parameters={courseID};
		System.out.println(parameters);
		ArrayList<Course> list = sqlhelper.executeQuery(sql, parameters);
		return list;
		
	}
	
	public static void main(String[] args) {
		Course course = new Course();
		course.setCourseID("2");
		course.setCourseName("WebøŒ…Ë");
		course.setTeacherID("100011");
		course.setClassroomID("101");
		course.setStarttime("1");
		course.setEndtime("9");
		
		CourseDao courseDao = new CourseDao();
//		courseDao.insertCourse(course);
		
		ArrayList<Course> course1 = courseDao.selectSingleCourse("1");
		System.out.println(course1.get(0) + "2222");
	}
}
