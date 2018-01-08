package teacher.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DefaultEditorKit.InsertTabAction;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import manager.dao.ManagerDAO;
import manager.entity.Department;
import net.sf.json.JSONArray;
import teacher.dao.TeacherDao;

public class TeacherAction extends ActionSupport{

	public void insertSingleCourse() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String result;
		String courseID = request.getParameter("courseID");
		String courseName = request.getParameter("courseName");
		String teacherID = request.getParameter("teacherID");
		String classroomID = request.getParameter("courseID");
		String starttime = request.getParameter("courseID");
		String endtime = request.getParameter("courseID");
		
		String courseTime = request.getParameter("courseTime");
		
		TeacherDao teacherDao = new TeacherDao();
		result = teacherDao.insertCourse(courseID, courseName, teacherID, 
				classroomID, starttime, endtime);
		
		insertCourseTime(courseTime, courseID);
		
		out.write(result);
		out.flush();
		out.close();
	}
	
	/**
	 * 添加课程时刻表
	 * @param courseTime 传入的为1,4,9以逗号为分隔符的字符串，1表示周一第一节，9表示周三第一节
	 */
	private void insertCourseTime(String courseTime, String courseID) {
		String courseTimeID[] = courseTime.split(",");
		
		for (String singleCourseTime : courseTimeID) {
			
			int single = Integer.parseInt(singleCourseTime);
			int week;
			int day;
			week = ( ( single % 4 == 0) ? 
					( single/4 ) : ( single/4 + 1 ) );
			day = ( ( single % 4 == 0) ? 
					( 4 ) : ( single % 4 ) );
			
			TeacherDao teacherDao = new TeacherDao();
			teacherDao.insertCourseTime(courseID, week + "", day + "");
		}
	}
	

}
