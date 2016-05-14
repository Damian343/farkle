package FarkleGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class gameBoard extends JFrame {
	private ArrayList<Player> players = new ArrayList<Player>();
	private JPanel contentPane;
	private JTextField textField;
	private int dice[];
	private static JLabel dice1, dice2, dice3, dice4, dice5, dice6;
	private int numDice;
	
	//called from main menu, creates the players, starts game
	public gameBoard(String nameOne, String nameTwo, boolean buyIn) {
		setTitle("Damians farkle");
		players.add(new Player(nameOne));
		players.add(new Player(nameTwo));
		dice = new int[6];
		numDice = 6;
		createWindow();
	}
	//creates the gameboard where all info is displayed
	private void createWindow() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
				e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlayerScore = new JLabel("Player 1 Score");
		lblPlayerScore.setBounds(292, 122, 76, 14);
		contentPane.add(lblPlayerScore);
		
		JLabel lblPlayerScore_1 = new JLabel("Player 2 score");
		lblPlayerScore_1.setBounds(292, 160, 71, 14);
		contentPane.add(lblPlayerScore_1);
		
		JLabel lblAvailableCombinations = new JLabel("Available Combinations");
		lblAvailableCombinations.setBounds(24, 122, 119, 14);
		contentPane.add(lblAvailableCombinations);
		
		JLabel lblCombinations = new JLabel("Combinations");
		lblCombinations.setBounds(158, 122, 76, 14);
		contentPane.add(lblCombinations);
		
		JLabel lblAvailablePointsIn = new JLabel("available points in roll");
		lblAvailablePointsIn.setBounds(24, 160, 119, 14);
		contentPane.add(lblAvailablePointsIn);
		
		JLabel lblRollpoints = new JLabel("rollPoints");
		lblRollpoints.setBounds(158, 160, 57, 14);
		contentPane.add(lblRollpoints);
		
		JLabel lblPleseSelectCombinations = new JLabel("Plese select combinations");
		lblPleseSelectCombinations.setBounds(10, 197, 138, 14);
		contentPane.add(lblPleseSelectCombinations);
		
		textField = new JTextField();
		textField.setBounds(139, 194, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFarkleCount = new JLabel("Farkle Count");
		lblFarkleCount.setBounds(292, 197, 71, 14);
		contentPane.add(lblFarkleCount);
		
		JLabel lblCount = new JLabel("Count");
		lblCount.setBounds(360, 197, 46, 14);
		contentPane.add(lblCount);
		
		JButton btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//diceRoll(dice1);diceRoll(dice2);
			}
		});
		btnRoll.setBounds(84, 227, 89, 23);
		contentPane.add(btnRoll);
		
		JButton btnPass = new JButton("Pass");
		btnPass.setBounds(260, 227, 89, 23);
		contentPane.add(btnPass);
		
		JLabel dice1 = new JLabel("yolo");
		dice1.setBounds(24, 46, 46, 42);
		contentPane.add(dice1);
		
		JLabel dice2 = new JLabel("yolo");
		dice2.setBounds(97, 46, 46, 42);
		contentPane.add(dice2);
		
		JLabel dice3 = new JLabel("yolo");
		dice3.setBounds(169, 46, 46, 42);
		contentPane.add(dice3);
		
		JLabel dice4 = new JLabel("yolo");
		dice4.setBounds(237, 46, 46, 42);
		contentPane.add(dice4);
		
		JLabel dice5 = new JLabel("yolo");
		dice5.setBounds(303, 46, 46, 42);
		contentPane.add(dice5);
		
		JLabel dice6 = new JLabel("yolo");
		dice6.setBounds(372, 46, 46, 42);
		contentPane.add(dice6);
		diceRoll(dice1);
		//messy need to clean
		setVisible(true);
	}
	//rolls dice adds that dice image to label
	public void diceRoll(JLabel dieLabel) {
		Random rnd = new Random();
		int n = rnd.nextInt(6) + 1;

		//gets the wanted image stores it as icon then places in label
		String neededImage = "C:\\Users\\damian\\Desktop\\lab11\\dice" + n + ".png";

		dieLabel.setIcon(new ImageIcon(neededImage));
		dice[n-1]++;
	}
}
