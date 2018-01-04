package student.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	/**
	 * Ñ§ÉúµÇÂ¼
	 */
	private static final long serialVersionUID = 1L;
	private int studentID;
	private String password;
	private String result;
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
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
		
		return SUCCESS;
	}
}
