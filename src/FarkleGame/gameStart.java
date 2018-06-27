package FarkleGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class gameStart extends JFrame{
	private final JTextField playerOneName;
	private final JTextField playerTwoName;

	private gameStart() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 193, 250);
		JPanel contentPane = new JPanel();
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
		btnStartGame.addActionListener((ActionEvent e) -> {
			String nameOne = playerOneName.getText();
			String nameTwo = playerTwoName.getText();
			boolean buyIn = buyInBttn.isSelected();
			start(nameOne, nameTwo, buyIn);
		});
	}
	
	private void start(String nameOne, String nameTwo, boolean buyIn){
		dispose();
		String[] names = {nameOne, nameTwo};
		Game game = new Game(names);
		game.newGame();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				gameStart frame = new gameStart();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
