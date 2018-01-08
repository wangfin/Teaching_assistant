package manager.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import manager.dao.ManagerDAO;
import manager.entity.Department;
import net.sf.json.JSONArray;

public class DepartmentAction extends ActionSupport{

	public void getAllDepartment() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Department> departments = managerDAO.getAllDepartment();
		
		out.write(JSONArray.fromObject(departments).toString());
		out.flush();
		out.close();
	}
	
	public void getSingleDepartment() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String departmentID = request.getParameter("departmentID");
		
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Department> departments = managerDAO.getSingleDepartment(departmentID);
		
		out.write(JSONArray.fromObject(departments).toString());
		out.flush();
		out.close();
	}
	
	public void updateSingleDepartment() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String departmentID = request.getParameter("departmentID");
		String departmentName = request.getParameter("departmentName");
		
		ManagerDAO managerDAO = new ManagerDAO();
		String result = managerDAO.updateSingleDepartment(departmentName, departmentID);
		
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}
}
