package universal.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class CheckLoginAction extends ActionSupport{
	/**
	 * 检查是否已经登陆
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String check(){
		Map<String, Object> map = new HashMap<String, Object>();
		// 检查session
		// 查看是否有用户名
		if(ServletActionContext.getRequest().getSession().getAttribute("studentID") !=null){
			String studentName = ServletActionContext.getRequest().getSession().getAttribute("studentName").toString();// 取出存取的姓名
			map.put("result", studentName);
			map.put("identity", "student");
		}else if(ServletActionContext.getRequest().getSession().getAttribute("teacherID") !=null){
			String teacherName = ServletActionContext.getRequest().getSession().getAttribute("teacherName").toString();// 取出存取的姓名
			map.put("result", teacherName);
			map.put("identity", "teacher");
		}else if(ServletActionContext.getRequest().getSession().getAttribute("managerID") !=null){
			String managerName = ServletActionContext.getRequest().getSession().getAttribute("managerName").toString();// 取出存取的姓名
			map.put("result", managerName);
			map.put("identity", "manager");
		}else{
			map.put("result", "未登录");
			map.put("identity", "");
		}
		
		
		// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
		JSONObject json = JSONObject.fromObject(map);
		// 注意，这里的result为String类型，内容为：
		// "{name:"Chris",age:5,position:"tt"}"
		result = json.toString();
		
		return SUCCESS;
	}
}
