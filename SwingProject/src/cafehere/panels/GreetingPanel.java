package cafehere.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GreetingPanel extends JPanel {
	private JPanel northPanel = new JPanel();
	private JLabel titleLabel = new JLabel("주문 하시겠습니까?");
	
	private JPanel centerPanel = new JPanel();
	private JButton memberButton = new JButton("회원");
	private JButton nonMemberButton = new JButton("비회원");
	private Border border = new LineBorder(Color.BLACK, 1);
	
	public GreetingPanel() {
		setLayout(new BorderLayout());
		setBorder(border);
		setSize(320,200);		
		setNorthPanel();		
		setCenterPanel();
	}
	
	public void setNorthPanel() {
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH);
	}
	
	public void setCenterPanel() {
		centerPanel.setLayout(new GridLayout(1,2));
		centerPanel.add(memberButton);
		centerPanel.add(nonMemberButton);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	//Getter : 회원 버튼, 비회원 버튼
	
	public JButton getMemberButton() {
		return memberButton;
	}
	
	public JButton getNonMemberButton() {
		return nonMemberButton;
	}
}
