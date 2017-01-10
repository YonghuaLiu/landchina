package writeDown;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;




public class ToMySQL extends ToMySqlBaseDao{
	//-----往数据库插入多条数据---------------------------
	public int writeAll(List<List> rows){
		int allResult=0;
		for (int i = 0; i < rows.size(); i++) {
			List everyRow=rows.get(i);
			int result=insertOneRow(everyRow);
			allResult+=result;
		}
		return allResult;
	}
	//-----往数据库插入一条数据---------------------------
	public int insertOneRow(java.util.List<String> everyRow) {
		Connection conn=super.sql_connect();
		String str="";
		for(int i=0;i<everyRow.size()-1;i++){
			str+="'"+everyRow.get(i)+"',";
		}
		str+="'"+everyRow.get(everyRow.size()-1)+"'";
		String sql="insert into earth_result values("+str+")";
		System.out.println(sql);
		try {
			Statement sm=conn.createStatement();
			int result=sm.executeUpdate(sql);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			super.sql_disconnect(conn, null, null);
		}
	}
	//-------------------------------------------------

}
