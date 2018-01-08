package teacher.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import manager.entity.Course;
import util.SqlHelper;

public class TeacherDao {
	SqlHelper sqlhelper = new SqlHelper();
	
	/**
	 * 新增课程
	 * @param courseID
	 * @param courseName
	 * @param teacherID
	 * @param classroomID
	 * @param starttime
	 * @param endtime
	 */
	public String insertCourse(String courseID, String courseName, String teacherID,
			String classroomID, String starttime, String endtime) {

		String sql = "INSERT INTO T_course (courseID, courseName, teacherID, classroomID, starttime, endtime) "
				+ "VALUES (" + courseID + ", '" + courseName + "'," + teacherID + "," + classroomID + "," + starttime + "," + endtime + ")";
		int result = 0;
		try {
			result = sqlhelper.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}
		if(result > 0) {
			return "SUCCESS";
		} 
		return "FAILURE";
	}
	
	/**
	 * 新增课程对应的时刻表
	 * @param courseID
	 * @param weekTime
	 * @param dayTime
	 * @return
	 */
	public String insertCourseTime(String courseID,
			String weekTime, String dayTime) {
		String sql = "INSERT INTO T_coursetime ( courseID, weekTime, dayTime) "
				+ "VALUES ("  + courseID + "," + weekTime + "," + dayTime + ")";
		int result = 0;
		try {
			result = sqlhelper.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}
		if(result > 0) {
			return "SUCCESS";
		} 
		return "FAILURE";
	}
	

	/**
	 * 查询特定老师的课程
	 * @param teacherID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Course> getTeacherCourse(String teacherID) throws Exception{
		String sql = "SELECT * FROM T_course WHERE teacherID = " + teacherID;
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Course> courses = new ArrayList<Course>();
		
		while(set.next()) {
			Course course = new Course();
			String courseID = set.getString("courseID");
			String courseName = set.getString("courseName");
			String classroomID = set.getString("classroomID");
			String starttime = set.getString("starttime");
			String endtime = set.getString("endtime");
			course.setCourseID(courseID);
			course.setCourseName(courseName);
			course.setClassroomID(classroomID);
			course.setStarttime(starttime);
			course.setEndtime(endtime);
			course.setTeacherID(teacherID);
			courses.add(course);
		}
			
		return courses;
	} 
}
