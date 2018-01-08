package manager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import manager.dao.ManagerDAO;
import manager.entity.Teacher;
import net.sf.json.JSONArray;

public class TeacherAction {
	
	public void getAllTeacher() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Teacher> teachers = managerDAO.getAllTeacher();
		
		out.write(JSONArray.fromObject(teachers).toString());
		out.flush();
		out.close();
	}
	
	public void getSingleTeacher() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String teacherID = request.getParameter("teacherID");
		
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Teacher> teachers = managerDAO.getSingleTeacher(teacherID);
		
		out.write(JSONArray.fromObject(teachers).toString());
		out.flush();
		out.close();
	}
	
	public void updateSingleTeacher() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String teacherName = request.getParameter("teacherName");
		String teacherID = request.getParameter("teacherID");
		String majorID = request.getParameter("majorID");
		
		ManagerDAO managerDAO = new ManagerDAO();
		String result = managerDAO.updateSingleTeacher(teacherID, teacherName, majorID);
		
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}
}
