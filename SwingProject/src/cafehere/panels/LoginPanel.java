package cafehere.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LoginPanel extends JPanel {
	private JLabel titleLabel = new JLabel("로그인 화면");
	
	private JLabel idLabel = new JLabel("아이디   ");
	private JTextField idTextField = new JTextField(15);
	private JLabel pwdLabel = new JLabel("비밀번호");
	private JTextField pwdTextField = new JTextField(15);
	
	private JButton loginButton = new JButton("로그인");
	private JButton signupButton = new JButton("회원가입");
	
	public LoginPanel() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setSize(350,300);
		setComponentFonts();
		setThisPanel();	
	}
	
	public void setComponentFonts() {
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 45));
		
		idLabel.setFont(new Font("맑은 고딕",Font.BOLD, 25));
		idTextField.setFont(new Font("맑은 고딕",Font.BOLD, 15));
		
		pwdLabel.setFont(new Font("맑은 고딕",Font.BOLD, 25));
		pwdTextField.setFont(new Font("맑은 고딕",Font.BOLD, 15));
		
		loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		signupButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	}
	
	public void setThisPanel() {
		add(titleLabel);
		
		add(idLabel);
		add(idTextField);
		
		add(pwdLabel);
		add(pwdTextField);
		
		add(loginButton);
		add(signupButton);
	}
	
	// Getter : 로그인 버튼, 회원가입 버튼, 로그인 id, 로그인 비밀번호
	
	public JButton getLoginButton() {
		return loginButton;
	}
	
	public JButton getSignupButton() {
		return signupButton;
	}
	
	public String getLoginId() {
		return idTextField.getText();
	}
	
	public String getLoginPassword() {
		return pwdTextField.getText();
	}
	
	//로그인 후 입력받았던 id,비밀번호 TextField초기화
	public void emptyLoginTextField() {
		idTextField.setText("");
		pwdTextField.setText("");
	}
	
}
