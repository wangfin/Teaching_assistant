package manager.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import manager.entity.Department;
import manager.entity.Major;
import util.SqlHelper;

public class ManagerDAO {
	SqlHelper sqlhelper = new SqlHelper();
	
	/**
	 * 获取所有的学院
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Department> getAllDepartment() throws Exception{
		String sql = "select * from T_department ORDER BY departmentID";
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Department> departments = new ArrayList<Department>();
		
		while(set.next()) {
			Department department = new Department();
			String id = set.getString("departmentID");
			String name = set.getString("departmentName");
			department.setDepartmentID(id);
			department.setDepartmentName(name);
			departments.add(department);
		}
			
		return departments;
	}
	
	/**
	 * 获取单个学院
	 * @param departmentID 学院ID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Department> getSingleDepartment(String departmentID) throws Exception{
		String sql = "select * from T_department WHERE departmentID = " + departmentID;
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Department> departments = new ArrayList<Department>();
		
		while(set.next()) {
			Department department = new Department();
			String id = set.getString("departmentID");
			String name = set.getString("departmentName");
			department.setDepartmentID(id);
			department.setDepartmentName(name);
			departments.add(department);
		}
			
		return departments;
	} 
	
	/**
	 * 
	 * @param departmentName
	 * @param departmentID
	 * @throws Exception
	 */
	public void updateSingleDepartment(String departmentName, String departmentID) throws Exception{
		String sql = "UPDATE T_department SET departmentName = "+ departmentName +" WHERE departmentID = " + departmentID;
		
		ResultSet set = sqlhelper.executeQuery(sql);
		
	} 
	
	/**
	 * 获取所有专业
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Major> getAllMajor() throws Exception{
		String sql = "select * from majorView ORDER BY majorID";
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Major> majors = new ArrayList<Major>();
		
		while(set.next()) {
			Major major = new Major();
			String id = set.getString("majorID");
			String majorName = set.getString("majorName");
			String departmentName = set.getString("departmentName");
			String departmentID = set.getString("departmentID");
			major.setMajorID(id);
			major.setMajorName(majorName);
			major.setDepartmentName(departmentName);
			major.setDepartmentID(departmentID);
			majors.add(major);
		}
			
		return majors;
	}
	
	/**
	 * 获取单个专业信息
	 * @param majorID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Major> getSingleMajor(String majorID) throws Exception{
		String sql = "select * from majorView WHERE majorID = " + majorID;
		
		ResultSet set = sqlhelper.executeQuery(sql);
		ArrayList<Major> majors = new ArrayList<Major>();
		
		while(set.next()) {
			Major major = new Major();
			
			String majorName = set.getString("majorName");
			String departmentID = set.getString("departmentID");
			String departmentName = set.getString("departmentName");
			major.setMajorID(majorID);
			major.setDepartmentID(departmentID);
			major.setDepartmentName(departmentName);
			major.setMajorName(majorName);
			
			majors.add(major);
		}
			
		return majors;
	} 

}
