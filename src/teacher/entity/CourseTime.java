package teacher.entity;

import manager.entity.Course;

public class CourseTime{
	private String coursetimeID;
	private String courseID;
	private String weekTime;
	private String daytime;
	public String getCoursetimeID() {
		return coursetimeID;
	}
	public void setCoursetimeID(String coursetimeID) {
		this.coursetimeID = coursetimeID;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getWeekTime() {
		return weekTime;
	}
	public void setWeekTime(String weekTime) {
		this.weekTime = weekTime;
	}
	public String getDaytime() {
		return daytime;
	}
	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}
	
	
}
