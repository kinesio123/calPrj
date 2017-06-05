package server.entity;
import server.vo.*;
import java.sql.*;
import java.util.*;

public class CalEntityImpl implements ICalEntity {
	private String url;
	private String user;
	private String pass;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<String> resultList;
	private String sql;

	public void setUrl(String url) {
		this.url=url;
	}
	public void setUser(String user) {
		this.user=user;
	}
	public void setPass(String pass) {
		this.pass=pass;
	}
	
	public CalEntityImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> doService(CalVO[] cals) throws Exception {
		con = DriverManager.getConnection(url,user,pass);	
		insert(cals);
		con = DriverManager.getConnection(url,user,pass);	
		resultList = select();
		return resultList;
	}


	public void insert(CalVO[] cals) throws Exception {
		try {	
			sql = "insert into tb_cal (op1, op, op2, result) values (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			for (int i=0; i< cals.length; i++) {
				pstmt.setString(1, cals[i].getOp1());
				pstmt.setString(2, cals[i].getOp());
				pstmt.setString(3, cals[i].getOp2());
				pstmt.setInt(4, cals[i].getResult());
				pstmt.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {										
			if (con != null) {			
	    		try {
	    			con.close();
	    			if (pstmt != null) {	
	    	    		try {
	    	    			pstmt.close();	
	    	    		} catch (SQLException e) {
	    	    			e.printStackTrace();
	    	    		}
	    			}
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
			} 
		}	
	}
	
	public List<String> select() {
		try {
			resultList = new ArrayList<String>();
			sql = "select * from tb_cal order by id desc limit 4";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String resultString;
			while(rs.next()) {
				int id=rs.getInt("id");
				int op1=rs.getInt("op1");
				String op=rs.getString("op").trim();
				int op2=rs.getInt("op2");
				int result=rs.getInt("result");
				resultString = id + ") " + op1 + " " + op + " " + op2 + " = " + result;
				resultList.add(resultString);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {			
	    		try {
	    			con.close();
	    			if (pstmt != null) {	
	    	    		try {
	    	    			pstmt.close();	
	    	    			 if (rs != null) {		
	    	    		    		try {
	    	    		    			rs.close();
	    	    		    		} catch (SQLException e) {
	    	    		    			e.printStackTrace();
	    	    		    		}
	    	    			 }
	    	    		} catch (SQLException e) {
	    	    			e.printStackTrace();
	    	    		}
	    			}
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
			} 
		}
		return resultList;
	}
	
	@Override
	public String getMsgAddZeroException() {
		return getExcMsgDB(97);
	}
	
	@Override
	public String getMsgSubZeroException() {
		return getExcMsgDB(98);
	}
	
	@Override
	public String getMsgMulOneException() {
		return getExcMsgDB(99);
	}
	
	@Override
	public String getMsgDivOneException() {
		return getExcMsgDB(100);
	}
	
	public String getExcMsgDB(int i) {	
		String excMsg = "";
		try {
			con = DriverManager.getConnection(url,user,pass);		
			String query = "select msg from tb_msg where code = "+i;
			pstmt = con.prepareStatement(query);	
			rs = pstmt.executeQuery();
			rs.next();
			excMsg = rs.getString("msg").trim();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
	    	if (con != null) {			
	    		try {
	    			con.close();
	    			if (pstmt != null) {	
	    	    		try {
	    	    			pstmt.close();	
	    	    			 if (rs != null) {		
	    	    		    		try {
	    	    		    			rs.close();
	    	    		    		} catch (SQLException e) {
	    	    		    			e.printStackTrace();
	    	    		    		}
	    	    			 }
	    	    		} catch (SQLException e) {
	    	    			e.printStackTrace();
	    	    		}
	    			}
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
			} 
		}
		return excMsg;
	}
	
}