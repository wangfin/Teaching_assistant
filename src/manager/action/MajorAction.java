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
import manager.entity.Major;
import net.sf.json.JSONArray;

public class MajorAction extends ActionSupport{

	public void getAllMajor() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Major> majors = managerDAO.getAllMajor();
		
		out.write(JSONArray.fromObject(majors).toString());
		out.flush();
		out.close();
	}
	
	public void getSingleMajor() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String majorID = request.getParameter("majorID");
		
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Major> majors = managerDAO.getSingleMajor(majorID);
		
		out.write(JSONArray.fromObject(majors).toString());
		out.flush();
		out.close();
	}
}
