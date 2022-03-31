package cafehere.dto;

import java.util.Date;

public class CafeUser {
	private int userNumber;
	private String userId;
	private String userPassword;
	private String userName;
	private String phoneNum;
	private Date createDate;
	private Date currentOrderDate;
	private int userMoney;
	
	public CafeUser() {}
	
	public CafeUser(String userId, String userPassword, String userName, String phoneNum) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.phoneNum = phoneNum;
	}

	public CafeUser(int userNumber, String userId, String userPassword, String userName,
					String phoneNum, int userMoney, Date createDate, Date currentOrderDate) {
		this.userNumber = userNumber;
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.createDate = createDate;
		this.userMoney = userMoney;
		this.currentOrderDate = currentOrderDate;
	}

	public int getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(int userMoney) {
		this.userMoney = userMoney;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCurrentOrderDate() {
		return currentOrderDate;
	}

	public void setCurrentOrderDate(Date currentOrderDate) {
		this.currentOrderDate = currentOrderDate;
	}

	@Override
	public String toString() {
		return "CafeUser [userNumber=" + userNumber + ", userId=" + userId + ", userPassword=" + userPassword
				+ ", userName=" + userName + ", phoneNum=" + phoneNum + ", userMoney=" + userMoney +", createDate=" + createDate
				+ ", currentOrderDate=" + currentOrderDate + "]";
	}
}
