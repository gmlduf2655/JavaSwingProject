package cafehere.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cafehere.dto.CafeMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CafeMenuDao {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver"; 
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private static final String ID = "c##user01"; 
	private static final String PASSWORD = "1234"; 
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	private static CafeMenuDao instance;	
	private CafeMenuDao() {}
	
	public static CafeMenuDao getInstance() {
		if(instance == null) {
			instance = new CafeMenuDao();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		try {
			Class.forName(DRIVER_NAME);
		} catch(ClassNotFoundException cnfe) {
			System.out.println("DRIVER_NAME이 올바르지 않음");
		} 
		return DriverManager.getConnection(URL, ID, PASSWORD);
	}
	
	public void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		if(connection != null) {try{connection.close();} catch(Exception e) {}};
		if(preparedStatement != null) {try{preparedStatement.close();} catch(Exception e) {}};
		if(resultSet != null) {try{resultSet.close();} catch(Exception e) {}};
	}
	
	public List<CafeMenu> getCafeMenuList(){
		List<CafeMenu> list = new ArrayList<>();
		
		try {
			connection = getConnection();
			
			String sql = "select * from cafe_menu";
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int menuNumber = resultSet.getInt("menu_number");
				String menuName = resultSet.getString("menu_name");
				int menuPrice = resultSet.getInt("menu_price");
				Date createDate = resultSet.getDate("create_date");
				
				CafeMenu cafeMenu = new CafeMenu(menuNumber, menuName, menuPrice, createDate);
				list.add(cafeMenu);
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}
		
		return list;
	} 
}
