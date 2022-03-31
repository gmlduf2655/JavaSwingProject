package cafehere.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cafehere.dao.CafeMenuDao;
import cafehere.dao.CafeUserDao;
import cafehere.dto.CafeMenu;

public class OrderPanel extends JPanel {
	private JPanel northPanel = new JPanel();
	
	private JPanel centerPanel = new JPanel();
	private MenuPanel[][] menus = new MenuPanel[4][2];
	
	private JPanel southPanel = new JPanel();
	private JLabel userMoneyLabel = new JLabel();
	private JTextField moneyTextField = new JTextField("0", 10);
	private JTextField spendTextField = new JTextField("0", 10);
	
	
	private JPanel eastPanel = new JPanel();
	private JButton orderButton = new JButton("주문하기");
	private JButton chargeButton = new JButton("금액충전");
	private JButton orderCancelButton = new JButton("주문취소");	
	
	private String userName;
	
	private CafeMenuDao cafeMenuDao;
	private List<CafeMenu> cafeMenuList;
	private List<String> menuNames = new ArrayList<>();
	private List<Integer> menuPrices = new ArrayList<>();
	private List<Integer> menuNumbers = new ArrayList<>();	
	
	public OrderPanel() {
		setLayout(new BorderLayout(10,5));
		setSize(850,550);	
		setNorthPanel();
		setCenterPanel();
		setSouthPanel();
		setEastPanel();
	}
	
	public void setNorthPanel() {
		northPanel.add(new JLabel("주문하기"));	
		add(northPanel, BorderLayout.NORTH);
	}
	
	public void setCenterPanel() {
		cafeMenuDao = CafeMenuDao.getInstance();
		cafeMenuList = cafeMenuDao.getCafeMenuList();
		for(CafeMenu cafeMenu : cafeMenuList) {
			menuNames.add(cafeMenu.getMenuName());
			menuPrices.add(cafeMenu.getMenuPrice());
		}
		
		int index = 0;
		for(int i=0 ; i<menus.length ; i++) {
			for(int j=0 ; j<menus[i].length ; j++) {
				menus[i][j] = new MenuPanel(index, menuNames, menuPrices, spendTextField);
				centerPanel.add(menus[i][j]);
				index++;
			}
		}	
		centerPanel.setLayout(new GridLayout(4,2,5,5));
		centerPanel.setBackground(Color.WHITE);
		add(new JScrollPane(centerPanel), BorderLayout.CENTER);
	}
	
	public void setSouthPanel() {
		moneyTextField.setEditable(false);
		moneyTextField.setBackground(Color.WHITE);
		spendTextField.setEditable(false);
		spendTextField.setBackground(Color.WHITE);
		
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		southPanel.add(userMoneyLabel);
		southPanel.add(moneyTextField);
		southPanel.add(new JLabel("결제 금액"));
		southPanel.add(spendTextField);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	public void setEastPanel() {
		eastPanel.setLayout(new BoxLayout(eastPanel ,BoxLayout.Y_AXIS));
		eastPanel.add(orderButton);
		eastPanel.add(chargeButton);		
		eastPanel.add(orderCancelButton);		
		add(eastPanel, BorderLayout.EAST);
	}
	
	// Getter : 주문버튼, 요금충전버튼, 메뉴 이름 리스트, 메뉴 주문 숫자 리스트, 메뉴 가격 리스트
	// Setter : 사용자이름
	// Getter & Setter : 사용자 잔액, 사용자가 지출할 돈
	
	
	public JButton getOrderButton() {
		return orderButton;
	}
	
	public JButton getChageButton() {
		return chargeButton;
	}
	
	public JButton getOrderCancelButton() {
		return orderCancelButton;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
		userMoneyLabel.setText(userName + " 님 현재 잔액");
	}
	
	
	public void setUserMoney(int userMoney) {
		moneyTextField.setText(userMoney + "");
	}
	
	public int getUserMoney() {
		return Integer.parseInt( moneyTextField.getText() );
	}
	
	public void setSpendMoney(int spendMoney) {
		spendTextField.setText(spendMoney + "");
	}
	
	public int getSpendMoney() {
		return Integer.parseInt( spendTextField.getText() );
	}
	
	public List<Integer> getMenuNumbers() {
		for(int i=0 ; i<menus.length ; i++) {
			for(int j=0 ; j<menus[i].length ; j++) {
				menuNumbers.add(menus[i][j].getMenuNum());
			}
		}		
		return menuNumbers;
	}
	
	public List<Integer> getMenuPrices() {
		return menuPrices;
	}
	
	public List<String> getMenuNames() {
		return menuNames;
	}
	
	//주문 후 메뉴 선택 숫자 초기화
	public void menuNumbersInitialize() {
		menuNumbers.clear();		
		for(int i=0 ; i<menus.length ; i++) {
			for(int j=0 ; j<menus[i].length ; j++) {
				menus[i][j].setMenuNum(0);
			}
		}
	}
	
}
