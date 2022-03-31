package cafehere.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cafehere.dao.CafeMenuDao;
import cafehere.dao.CafeUserDao;
import cafehere.dto.CafeMenu;
import cafehere.dto.CafeUser;
import cafehere.panels.GreetingPanel;
import cafehere.panels.ImagePanel;
import cafehere.panels.LoginPanel;
import cafehere.panels.OrderPanel;
import cafehere.panels.ResultPanel;
import cafehere.panels.SignupPanel;

public class MainFrame extends JFrame implements ActionListener {
	private Container c = getContentPane();
	private JLayeredPane layer = new JLayeredPane();
	
	private GreetingPanel greetingPanel = new GreetingPanel();
	private JButton memberButton;
	private JButton nonMemberButton;
	
	private LoginPanel loginPanel = new LoginPanel();
	private JButton loginButton;
	private JButton signupButton;
	
	private SignupPanel signupPanel = new SignupPanel();
	private JButton cancelButton;
	private JButton signupConfirmButton;
	
	private OrderPanel orderPanel = new OrderPanel();
	private JButton orderButton;
	private JButton chargeButton;
	private JButton orderCancelButton;
	
	private ResultPanel resultPanel = new ResultPanel();
	private JButton backButton;
	private JButton exitButton;
	
	private CafeUserDao cafeUserDao;
	private CafeUser loginCafeUser;
	
	ImagePanel imagePanel = new ImagePanel();	
	
	public MainFrame() {
		
		setTitle("카페 주문");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c.setLayout(null);
	
		c.add(greetingPanel);
		greetingPanel.setBackground(Color.WHITE);
		
		setBackGround();
		
		getButtons();
		addListenerToButtons();
		
		setSize(900,600);
		setVisible(true);
		
		alignPanelsCenter();
		
		cafeUserDao = CafeUserDao.getInstance();
	}
	
	public void setBackGround() {
		layer.setSize(900, 600);
		imagePanel.setSize(900, 600);
		imagePanel.setOpaque(false);
		layer.add(imagePanel, new Integer(0));
		layer.setOpaque(false);
		c.add(layer);
	}
	
	public void getButtons() {
		memberButton = greetingPanel.getMemberButton();
		nonMemberButton = greetingPanel.getNonMemberButton();
		
		loginButton = loginPanel.getLoginButton();
		signupButton = loginPanel.getSignupButton();
		
		cancelButton = signupPanel.getCancelButton();
		signupConfirmButton = signupPanel.getSignupConfirmButton();
		
		orderButton = orderPanel.getOrderButton();
		chargeButton = orderPanel.getChageButton();
		orderCancelButton = orderPanel.getOrderCancelButton();
		
		backButton = resultPanel.getBackButton();
		exitButton = resultPanel.getExitButton();
	}
	
	public void addListenerToButtons() {
		memberButton.addActionListener(this);
		nonMemberButton.addActionListener(this);
		
		loginButton.addActionListener(this);
		signupButton.addActionListener(this);
		
		cancelButton.addActionListener(this);
		signupConfirmButton.addActionListener(this);
		
		orderButton.addActionListener(this);
		chargeButton.addActionListener(this);
		orderCancelButton.addActionListener(this);
		
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
	}

	
	public void alignPanelsCenter() {
		alignPanelCenter(greetingPanel);
		alignPanelCenter(loginPanel);
		alignPanelCenter(signupPanel);
		alignPanelCenter(orderPanel);
		alignPanelCenter(resultPanel);
	}
	
	public void alignPanelCenter(JPanel panel) {
		panel.setLocation( ( c.getWidth()-panel.getWidth() )/2,
							( c.getHeight()-panel.getHeight() )/2 );
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.removeAll();
		Object source = e.getSource();
		//String buttonText =e.getActionCommand();
		
		// GreetingPanel에 있는 버튼 액션 -> 회원 버튼, 비회원 버튼
		if(source == memberButton) {
			c.add(loginPanel);
		} else if(source == nonMemberButton) {
			loginCafeUser = null;
			orderPanel.setUserName("비회원");
			orderPanel.setUserMoney(0);
			orderPanel.setSpendMoney(0);
			c.add(orderPanel);
		} else {}
		
		// LoginPanel에 있는 버튼 액션 -> 로그인 버튼, 회원가입 버튼
		if(source == loginButton) {		
			String loginId = loginPanel.getLoginId();
			String loginPassword = loginPanel.getLoginPassword();
			loginCafeUser = cafeUserDao.getUserInfoByLogin(loginId, loginPassword);
			System.out.println(loginCafeUser);
			// 로그인 오류가 났을 때 (DB에서 해당 아이디와 비밀번호를 조회하지 못했을 떄) -> if
			// 로그인이 잘 되었을 때 (DB에서 해당 아이디와 비밀번호를 조회했을 때) -> else
			if(loginCafeUser == null) {
				JOptionPane.showMessageDialog(null, "아이디나 패스워드가 잘못되었거나 존재하지 않습니다", "로그인 오류", JOptionPane.ERROR_MESSAGE);
				c.add(loginPanel);
			}else {
				loginPanel.emptyLoginTextField();
				String loginUserName = loginCafeUser.getUserName();
				int loginUserMoney = loginCafeUser.getUserMoney();
				
				orderPanel.setUserName(loginUserName);
				orderPanel.setUserMoney(loginUserMoney);
				orderPanel.setSpendMoney(0);
				c.add(orderPanel);
			}
		} else if(source == signupButton) {			
			c.add(signupPanel);
		} else {} 
		
		// SignupPanel에 있는 버튼 액션 -> 취소 버튼, 회원가입 버튼
		if(source == cancelButton) {
			c.add(greetingPanel);
		} else if(source == signupConfirmButton) {
			CafeUser signupCafeUser = signupPanel.getSignupCafeUser();
			String signupUserName = signupCafeUser.getUserName();
			String singupUserId = signupCafeUser.getUserId();
			String signupUserPassword = signupCafeUser.getUserPassword();
			String singupUserPhoneNum = signupCafeUser.getPhoneNum();
			
			if(signupUserName.length() < 2 || singupUserId.length() < 4 || 
					signupUserPassword.length() < 4 || singupUserPhoneNum.length() != 13) {
				JOptionPane.showMessageDialog(null, "아이디, 비밀번호는 4글자 이상, 연락처는 000-0000-0000 형식으로, 이름은 2글자 이상 입력해주세요", 
										"회원가입 오류", JOptionPane.ERROR_MESSAGE);
				c.add(signupPanel);
			}else {
				cafeUserDao.signupUser(signupCafeUser);
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다", 
						"회원가입 성공", JOptionPane.PLAIN_MESSAGE);
				c.add(loginPanel);
			}
		} else {}
		
		// OrderPanel에 있는 버튼 액션 -> 주문 버튼, 요금 충전 버튼, 주문 취소 버튼
		if(source == orderButton) {	
			int userMoney = orderPanel.getUserMoney();
			int spendMoney = orderPanel.getSpendMoney();
			int change = userMoney - spendMoney;
			//주문을 하지 않았을 때
			if(spendMoney == 0) {
				JOptionPane.showMessageDialog(null, "주문 해주세요", 
						"주문 오류", JOptionPane.ERROR_MESSAGE);
				c.add(orderPanel);
				
				c.add(layer);
				c.repaint();
				setVisible(true);
				return;
			}
			
			// 요금이 모자랄 때 -> if
			// 요금이 충분할 떄 -> else
			if(change < 0){
				JOptionPane.showMessageDialog(null, "추가요금을 투입해 주세요", 
						"주문 오류", JOptionPane.ERROR_MESSAGE);
				c.add(orderPanel);
			}else {
				if(loginCafeUser != null) {
					loginCafeUser.setUserMoney(change);
					cafeUserDao.afterOrder(loginCafeUser);
					System.out.println(loginCafeUser);
				}
				List<Integer> menuPrices = orderPanel.getMenuPrices();
				List<Integer> menuNumbers = orderPanel.getMenuNumbers();
				List<String> menuNames = orderPanel.getMenuNames();
				String message = "메뉴\t개수\t가격\n";
				message += "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n";
				for(int i=0 ; i < menuNames.size() ; i++) {
					if(menuNumbers.get(i) >= 1) {
						message += menuNames.get(i) + "\t" + menuNumbers.get(i) + "\t" + menuPrices.get(i)*menuNumbers.get(i) + "\n";
					}
				}
				orderPanel.menuNumbersInitialize();
				
				resultPanel.setMoneyTextField(userMoney);
				resultPanel.setSpendTextField(spendMoney);
				resultPanel.setChangeTextField(change);
				resultPanel.setSpendContentTextArea(message);
				c.add(resultPanel);
			}
		} else if(source == chargeButton){
			try {
				String chargeStr = JOptionPane.showInputDialog("얼마를 충전하시겠습니까?");
				int charge = Integer.parseInt(chargeStr);
				int userMoney = orderPanel.getUserMoney() + charge;
				orderPanel.setUserMoney(userMoney);
				if(loginCafeUser != null) {
					loginCafeUser.setUserMoney(userMoney);		
					cafeUserDao.updateUserMoney(loginCafeUser);
					System.out.println(loginCafeUser);
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "숫자만 입력해주세요", 
						"요금 충전오류", JOptionPane.ERROR_MESSAGE);
			}
			c.add(orderPanel);
		} else if(source == orderCancelButton){
			c.add(greetingPanel);
		} else{}
		
		// ResultPanel에 있는 버튼 액션 -> 처음으로 버튼, 이용종료 버튼
		if(source == backButton) {
			c.add(greetingPanel);
		} else if(source == exitButton) {
			System.exit(0);
		} else {}
		
		c.add(layer);
		c.repaint();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}


}
