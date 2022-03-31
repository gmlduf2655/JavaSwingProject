package cafehere.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class ResultPanel extends JPanel {	
	private JPanel northPanel = new JPanel();
	private JLabel titleLabel = new JLabel("결제 결과");
	
	private JPanel centerPanel = new JPanel();
	private JTextField moneyTextField = new JTextField(25);
	private JTextField spendTextField = new JTextField(25);
	private JTextArea spendContentTextArea = new JTextArea(13,25);
	private JTextField changeTextField = new JTextField(25);
	
	private JPanel southPanel = new JPanel();
	private JButton backButton = new JButton("처음으로");
	private JButton exitButton = new JButton("이용종료");
	
	private Border border = new LineBorder(Color.BLACK, 1);
	
	private String spendContent;
	private int money;
	private int spend;
	private int change;
	
	public ResultPanel() {
		setLayout(new BorderLayout());
		setSize(350,400);
		setBorder(border);
		setNorthPanel();
		setCenterPanel();
		setSouthPanel();
	}
	
	public void setNorthPanel() {
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH);
		northPanel.setBackground(Color.WHITE);
	}
	
	public void setCenterPanel() {
		centerPanel.add(new JLabel("현재 잔액"));
		moneyTextField.setText(money + "");
		centerPanel.add(moneyTextField);
		centerPanel.add(new JLabel("결제 금액"));
		spendTextField.setText(spend + "");
		centerPanel.add(spendTextField);
		centerPanel.add(new JLabel("주문 내역"));
		spendContentTextArea.setText(spendContent);
		centerPanel.add(new JScrollPane( spendContentTextArea ));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.add(new JLabel("거스름돈"));
		
		changeTextField.setText(change + "");
		centerPanel.add(changeTextField);
		centerPanel.setBackground(Color.white);
	}
	
	public void setSouthPanel() {
		backButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		exitButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		southPanel.add(backButton);
		southPanel.add(exitButton);
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setBackground(Color.WHITE);
	}
	
	// Getter : 돌아가기 버튼, 이용종료 버튼
	// Setter : 사용자 잔액, 사용자가 지출할 금액, 거스름돈 텍스트 필드, 이용내역 텍스트 Area 
	
	public JButton getBackButton() {
		return backButton;
	}
	
	public JButton getExitButton() {
		return exitButton;
	}
	
	public void setMoneyTextField(int userMoney) {
		moneyTextField.setText(userMoney + "");
	}
	
	public void setSpendTextField(int spendMoney) {
		spendTextField.setText(spendMoney + "");
	}
	
	public void setChangeTextField(int change) {
		changeTextField.setText(change + "");
	}
	
	public void setSpendContentTextArea(String message) {
		spendContentTextArea.setText(message);
	}
}
