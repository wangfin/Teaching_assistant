package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	//驱动名 sqlserver
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//连接数据库的URL地址
	private static final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Teaching_assistant";
	private static final String username = "root";
	private static final String password = "root";
	//创建连接类
	//private static Connection conn = null;
	//静态代码块负责加载驱动
	static 
	{
		try
		{
			Class.forName(driver);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//单例模式返回数据库连接对象
	public static Connection getConnection() throws Exception
	{
		//此处为防止“连接已关闭”的错误，在这里创建Connection，为防止使用全局的Connection，而导致多线程并发访问错误
		Connection conn = null;
		if(conn == null)//如果没有创建,则创建对象
		{
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
	
	//关闭资源函数
	public static void close(ResultSet rs,Statement ps,Connection ct)
	{
		if(rs!=null)
		{	
				try
			{
					rs.close();
			}catch(Exception e)
			{
				
			}
			rs=null;//使用垃圾回收
		}
		if(ps!=null)
		{
			try
			{
					ps.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			ps=null;
		}
		
		if(ct!=null)
		{
			try
			{
					ct.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			ct=null;
		}
	}
}

