package cafehere.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import cafehere.dto.CafeUser;

public class CafeUserDao {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver"; 
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private static final String ID = "c##user01"; 
	private static final String PASSWORD = "1234"; 
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	String sql;
	
	private static CafeUserDao instance;
	
	private CafeUserDao() {}
	
	public static CafeUserDao getInstance() {
		if(instance == null) {
			instance = new CafeUserDao();
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
	
	public CafeUser getUserInfoByLogin(String loginId, String loginPassword) {
		CafeUser cafeUser = null;
		try {
			connection = getConnection();
			sql = "select * from cafe_user where user_id = ? and user_password = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, loginPassword);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int userNumber = resultSet.getInt("user_number");
				String userId = resultSet.getString("user_id");
				String userPassword = resultSet.getString("user_password");
				String userName = resultSet.getString("user_name");
				String phoneNum = resultSet.getString("phone_num");
				Date createDate = resultSet.getDate("create_date");
				int userMoney = resultSet.getInt("user_money");
				Date currentOrderDate = resultSet.getDate("current_order_date");
				
				cafeUser = new CafeUser(userNumber, userId, userPassword, userName, phoneNum, userMoney, createDate, currentOrderDate);
			}
			
		} catch(SQLException se) {
			System.out.println(se);
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}
		
		return cafeUser;
	}
	
	public int getUserNumbers() {
		int userNumbers = 0;
		try {
			connection = getConnection();
			sql = "select count(user_id) from cafe_user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				userNumbers = resultSet.getInt("count(user_id)");
			}
		} catch(SQLException se) {
			System.out.println(se);
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		} 
		return userNumbers;
	}
	
	public int signupUser(CafeUser cafeUser) {
		int success = 0;
		try {
			connection = getConnection();
			sql = "insert into cafe_user(user_number, user_id, user_password, user_name, phone_num, create_date, user_money)"
					+ "			values(user_number_sequence.nextval, ?, ?, ?, ?, sysdate, 0)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cafeUser.getUserId());
			preparedStatement.setString(2, cafeUser.getUserPassword());
			preparedStatement.setString(3, cafeUser.getUserName());
			preparedStatement.setString(4, cafeUser.getPhoneNum());
			success = preparedStatement.executeUpdate();
		} catch(SQLException se) {
			System.out.println(se);
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}
		return success;
	}
	
	public int updateUserMoney(CafeUser cafeUser) {
		int success = 0;
		try {
			connection = getConnection();
			sql = "update cafe_user set user_money=? where user_id=?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, cafeUser.getUserMoney());
			preparedStatement.setString(2, cafeUser.getUserId());
			
			success = preparedStatement.executeUpdate();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}		
		
		return success;
	}
	
	public int afterOrder(CafeUser cafeUser) {
		int success = 0;
		try {
			connection = getConnection();
			sql = "update cafe_user set user_money=?, current_order_date=sysdate where user_id=?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, cafeUser.getUserMoney());
			preparedStatement.setString(2, cafeUser.getUserId());
			
			success = preparedStatement.executeUpdate();
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}		
		
		return success;		
	}
}
