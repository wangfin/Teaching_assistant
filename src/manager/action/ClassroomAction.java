package manager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import manager.dao.ManagerDAO;
import manager.entity.Classroom;
import net.sf.json.JSONArray;

public class ClassroomAction extends ActionSupport{

	public void getAllClassroom() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Classroom> classrooms = managerDAO.getAllClassroom();
		
		out.write(JSONArray.fromObject(classrooms).toString());
		out.flush();
		out.close();
	}
	
	public void getSingleClassroom() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String classroomID = request.getParameter("classroomID");
		
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Classroom> classrooms = managerDAO.getSingleClassroom(classroomID);
		
		out.write(JSONArray.fromObject(classrooms).toString());
		out.flush();
		out.close();
	}
}
