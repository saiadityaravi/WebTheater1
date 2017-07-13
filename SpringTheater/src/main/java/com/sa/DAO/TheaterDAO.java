package com.sa.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sa.spring.DataBeanConfig;
import com.sa.spring.DataSource;

public class TheaterDAO {

	public static DataSource getConfig() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(DataBeanConfig.class);
		DataSource ds = ctx.getBean("getDataSource", DataSource.class);
		return ds;
	}
	
	public static Connection getConnection() throws SQLException{
		DataSource ds = getConfig();
		String driver=ds.getDriver();
		String url=ds.getUrl();
		String username=ds.getUsername();
		String password=ds.getPassword();
		Connection conn=null;
		try{
		Class.forName(driver);
		conn = DriverManager.getConnection(url,username,password);
		return conn;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int createTheater(String name,String address){
		int row;
		try {
			PreparedStatement ptst = getConnection().prepareStatement("insert into theater(theater_name,address) values(?,?)");
			ptst.setString(1, name);
			ptst.setString(2, address);
			row=ptst.executeUpdate();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static int createScreen(int id,int rows,int seats,String movie){
		int row;
		try {
			PreparedStatement ptst = getConnection().prepareStatement("insert into screen values(?,?,?,?)");
			ptst.setInt(1, id);
			ptst.setInt(2, rows);
			ptst.setInt(3, seats);
			ptst.setString(4, movie);
			row=ptst.executeUpdate();
			if(row==1){
				for (int i = 1; i < rows+1; i++) {
					for (int j = 1; j < seats+1; j++) {
						String row_id= String.valueOf((char)(i +64))+""+j;
						
						insertSeats(id,row_id);
					}
				}
				
			}
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static ResultSet getScreenList(){
		try {
			PreparedStatement ptst = getConnection().prepareStatement("select * from screen");
			ResultSet rs = ptst.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static int getCount(int screen_id){
		try {
			PreparedStatement ptst = getConnection().prepareStatement("select rows from screen where screenid=?");
			ptst.setInt(1, screen_id);
			ResultSet rs = ptst.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static ResultSet getTheater(){
		try {
			PreparedStatement ptst = getConnection().prepareStatement("select * from theater");
			ResultSet rs = ptst.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 public static void insertSeats(int id, String row_id){
		 String sql ="insert into seats values(?,?,?)";
		 try {
				PreparedStatement ptst=getConnection().prepareStatement(sql);
				ptst.setInt(1, id);
				ptst.setString(2, row_id);
				ptst.setBoolean(3, false);
				ptst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	 }
	public static ResultSet getSeats(int screenid){
		String sql ="select row_id, reseved from seats where screen_id=?";
		try {
			PreparedStatement ptst=getConnection().prepareStatement(sql);
			ptst.setInt(1, screenid);
			ResultSet rs = ptst.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int bookSeats(int screen_id,String booked) {
		String sql ="update seats set reseved=true where screen_id='"+screen_id+"' and row_id='"+booked+"'";
			try {
				PreparedStatement ptst=getConnection().prepareStatement(sql);
				System.out.println("update seats set reseved=true where screen_id='"+screen_id+"' and row_id='"+booked+"'");
				ptst.execute();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}
}
