package teacher.dao;

import java.util.ArrayList;

import student.entity.Student;
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
}
