package cafehere.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MenuPanel extends JPanel implements ActionListener {
	private JButton minusButton;
	private JLabel menuNumsLabel;
	private JButton plusButton;	
	private JTextField spendTextField;
	private List<String> menuNames;
	
	private int menuNum = 0;
	private int sum ;
	private int index;
	private List<Integer> menuPrices;
	
	public MenuPanel(int index, List<String> menuNames, List<Integer> menuPrices, JTextField spendTextField) {	
		this.index = index;
		this.spendTextField = spendTextField;
		this.menuNames = menuNames;
		this.menuPrices = menuPrices;			
		setThisPanel();
	}
	
	public void setThisPanel() {
		ImageIcon icon = new ImageIcon("images/"+ (index + 1) +".jpg");
		add(new JLabel(icon));
		add(new JLabel("   " + menuNames.get(index) + " " +menuPrices.get(index) +"원    "));
				
		minusButton = new JButton("-");
		minusButton.addActionListener(this);
		add(minusButton);
				
		menuNumsLabel = new JLabel(menuNum + "");
		add(menuNumsLabel);
				
		plusButton = new JButton("+");
		plusButton.addActionListener(this);
		add(plusButton);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		sum = Integer.parseInt( spendTextField.getText() );
		if(source == minusButton) {
			if(menuNum >=1) {
				menuNum--;
				menuNumsLabel.setText(menuNum + "");
				sum -= menuPrices.get(index);
				spendTextField.setText(sum + "");
			}
		}else if(source == plusButton) {
			menuNum++;
			menuNumsLabel.setText(menuNum + "");
			sum += menuPrices.get(index);
			spendTextField.setText(sum + "");
		}else {}
	};
	
	// Getter & Setter : 메뉴 숫자
	
	public int getMenuNum() {
		return menuNum;
	}
	
	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
		menuNumsLabel.setText(menuNum + "");
	}
}
