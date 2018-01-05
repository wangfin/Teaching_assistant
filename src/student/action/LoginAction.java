package student.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import student.dao.DBDAO;
import student.entity.Student;

public class LoginAction extends ActionSupport{
	/**
	 * 学生登录
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String result;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String login(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		DBDAO db = new DBDAO();
		//构建实体类，向DAO函数传递参数
		Student student = new Student();
		student.setStudentID(username);
		student.setPassword(password);
		//DAO的参数为实体类，返回值为ArrayList
		ArrayList<Student> students = db.checkStudent(student);
		// 检测是否有这个用户名
					if (students != null && students.size() > 0) {
						Student stu = students.get(0);
						// 检测密码
						if (password.equals(stu.getPassword())) {
							// 跟传统的action传值一样，这里的name,gender,position由struts接收前端传参后初始化，在action中直接使用就可以了。
							map.put("result", "成功");

							// 输入session，保持登录状态
							ServletActionContext.getRequest().getSession().setAttribute("studentID", stu.getStudentID());// 保存用户名
							ServletActionContext.getRequest().getSession().setAttribute("studentName",stu.getStudentName());// 保存姓名
						} else {
							map.put("result", "密码输入错误");
						}
					} else {
						map.put("result", "账号错误或不存在");
					}

					// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
					JSONObject json = JSONObject.fromObject(map);
					// 注意，这里的result为String类型，内容为：
					// "{name:"Chris",age:5,position:"tt"}"
					result = json.toString();

					return SUCCESS;
	}
}
