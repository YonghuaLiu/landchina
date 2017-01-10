package writeDown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



/** 定义连接数据库和关闭的方法 **/
public class ToMySqlBaseDao {
	public static final String URL = "jdbc:sqlserver://localhost:1433;DataBaseName=pachong_lanyx_earth";
	public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String USERNAME = "sa";
	public static final String PWD = "123456";

	//----------------------------连接数据库--------------------------
	public Connection sql_connect() {
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//----------------------------关闭数据库--------------------------
	public void sql_disconnect(Connection conn,Statement st,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(st!=null){
				st.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
