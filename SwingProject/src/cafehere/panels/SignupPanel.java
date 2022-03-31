package cafehere.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import cafehere.dto.CafeUser;

public class SignupPanel extends JPanel {
	private JLabel titleLabel = new JLabel("회원 가입");
	private JLabel idLabel = new JLabel("아이디  ");
	private JTextField idTextField = new JTextField(15);
	private JLabel pwdLabel = new JLabel("패스워드");
	private JTextField pwdTextField = new JTextField(15);
	private JLabel nameLabel = new JLabel("이름    ");
	private JTextField nameTextField = new JTextField(15);
	private JLabel phoneLabel = new JLabel("전화번호");
	private JTextField phoneTextField = new JTextField(15);
	
	private JButton cancelButton = new JButton("취소");
	private JButton signupConfirmButton = new JButton("회원가입");
	
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	
	private Border border = new LineBorder(Color.BLACK, 1);
	
	public SignupPanel() {
		setSize(350,400);
		setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setBackground(Color.WHITE);
		setBorder(border);
		setLayout(new BorderLayout());		
		setComponentFonts();		
		
		setNorthPanel();		
		setCenterPanel();						
		setSouthPanel();
	}
	
	public void setComponentFonts() {
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		idTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		pwdLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		pwdTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		nameTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		phoneLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		phoneTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		
		cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		signupConfirmButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	}
	
	public void setNorthPanel() {
		northPanel.add(titleLabel);
		northPanel.setBackground(Color.WHITE);
		add(northPanel, BorderLayout.NORTH);
	}
	
	public void setCenterPanel() {
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(idLabel);
		centerPanel.add(idTextField);
		centerPanel.add(pwdLabel);
		centerPanel.add(pwdTextField);
		centerPanel.add(nameLabel);
		centerPanel.add(nameTextField);
		centerPanel.add(phoneLabel);
		centerPanel.add(phoneTextField);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	public void setSouthPanel() {
		southPanel.setBackground(Color.WHITE);
		southPanel.add(cancelButton);
		southPanel.add(signupConfirmButton);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	// Getter : 회원가입 취소버튼, 회원가입 완료버튼, 회원 가입한 회원 정보
	
	public JButton getCancelButton() {
		return cancelButton;
	}
	
	public JButton getSignupConfirmButton() {
		return signupConfirmButton;
	}	
	
	public CafeUser getSignupCafeUser() {
		return new CafeUser(idTextField.getText(), pwdTextField.getText(), nameTextField.getText(), phoneTextField.getText());
	}
}
