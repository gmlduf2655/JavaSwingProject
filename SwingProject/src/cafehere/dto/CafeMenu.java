package cafehere.dto;

import java.util.Date;

public class CafeMenu {
	private int menuNumber;
	private String menuName;
	private int menuPrice;
	private Date createDate;
	
	public CafeMenu() {}
	
	public CafeMenu(int menuNumber, String menuName, int menuPrice, Date createDate) {
		this.menuNumber = menuNumber;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.createDate = createDate;
	}

	public int getMenuNumber() {
		return menuNumber;
	}

	public void setMenuNumber(int menuNumber) {
		this.menuNumber = menuNumber;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "CafeMenu [menuNumber=" + menuNumber + ", menuName=" + menuName + ", menuPrice=" + menuPrice
				+ ", createDate=" + createDate + "]";
	}
	
	
}
