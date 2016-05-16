package FarkleGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class gameStart extends JFrame{
	private JPanel contentPane;
	private JTextField playerOneName;
	private JTextField playerTwoName;

	public gameStart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 193, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToFarkle = new JLabel("Welcome to Farkle!");
		lblWelcomeToFarkle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWelcomeToFarkle.setBounds(36, 21, 108, 14);
		contentPane.add(lblWelcomeToFarkle);
		
		JRadioButton buyInBttn = new JRadioButton("Buyin(500)");
		buyInBttn.setHorizontalAlignment(SwingConstants.CENTER);
		buyInBttn.setBounds(48, 140, 92, 23);
		contentPane.add(buyInBttn);

		playerOneName = new JTextField("  Player Ones Name");
		playerOneName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				playerOneName.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		playerOneName.setBounds(36, 57, 108, 20);
		contentPane.add(playerOneName);
		playerOneName.setColumns(10);
		
		playerTwoName = new JTextField("  Player Twos Name");
		playerTwoName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				playerTwoName.setText("");
			}
			public void focusLost(FocusEvent e) {
			}
		});
		playerTwoName.setBounds(36, 104, 109, 20);
		contentPane.add(playerTwoName);
		playerTwoName.setColumns(10);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(36, 177, 109, 23);
		contentPane.add(btnStartGame);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameOne = playerOneName.getText();
				String nameTwo = playerTwoName.getText();
				boolean buyIn = buyInBttn.isSelected();
				start(nameOne, nameTwo, buyIn);
			}
		});
	}
	
	public void start(String nameOne, String nameTwo, boolean buyIn){
		dispose();
		gameBoard gameBoard = new gameBoard(nameOne, nameTwo, buyIn);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameStart frame = new gameStart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
