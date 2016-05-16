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
//each player class keeps track of score, random player is selected to go first
public class gameBoard extends JFrame {
	private ArrayList<Player> players = new ArrayList<Player>();
	private JPanel contentPane;
	private JTextField textField;
	private int[] diceSides;
	private int[] dice;
	private boolean buyIn;
	private Random random;
	private static JLabel dice1, dice2, dice3, dice4, dice5, dice6, currentPlayerlbl;
	private int numDice;
	
	//called from main menu, creates the players, starts game
	public gameBoard(String nameOne, String nameTwo, boolean buyIn) {
		setTitle("Damians farkle");
		players.add(new Player(nameOne));
		players.add(new Player(nameTwo));
		this.buyIn = buyIn;
		random = new Random(); 
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
		
		JLabel playerScorelbl = new JLabel("Players Score");
		playerScorelbl.setBounds(292, 122, 76, 14);
		contentPane.add(playerScorelbl);
		
		JLabel lblAvailableCombinations = new JLabel("Available Combinations: ");
		lblAvailableCombinations.setBounds(24, 122, 119, 14);
		contentPane.add(lblAvailableCombinations);
		
		JLabel combinationlbl = new JLabel("");
		combinationlbl.setBounds(158, 122, 76, 14);
		contentPane.add(combinationlbl);
		
		JLabel lblAvailablePointsIn = new JLabel("Total Roll Score: ");
		lblAvailablePointsIn.setBounds(24, 160, 119, 14);
		contentPane.add(lblAvailablePointsIn);
		
		JLabel rollPointslbl = new JLabel("");
		rollPointslbl.setBounds(158, 160, 57, 14);
		contentPane.add(rollPointslbl);
		
		JLabel lblPleseSelectCombinations = new JLabel("Plese select dice");
		lblPleseSelectCombinations.setBounds(10, 197, 138, 14);
		contentPane.add(lblPleseSelectCombinations);
		
		JLabel lblFarkleCount = new JLabel("Farkle Count");
		lblFarkleCount.setBounds(292, 197, 71, 14);
		contentPane.add(lblFarkleCount);
		
		JLabel farkleCountlbl = new JLabel("");
		farkleCountlbl.setBounds(360, 197, 46, 14);
		contentPane.add(farkleCountlbl);
		
		JButton btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diceRoll(dice1);diceRoll(dice2);diceRoll(dice3);diceRoll(dice4);diceRoll(dice5);diceRoll(dice6);
			}
		});
		btnRoll.setBounds(84, 227, 89, 23);
		contentPane.add(btnRoll);
		
		JButton btnPass = new JButton("Pass");
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passTurn();
			}
		});
		btnPass.setBounds(260, 227, 89, 23);
		contentPane.add(btnPass);
		
		dice1 = new JLabel("");
		dice1.setBounds(24, 46, 46, 42);
		contentPane.add(dice1);
		
		dice2 = new JLabel("");
		dice2.setBounds(97, 46, 46, 42);
		contentPane.add(dice2);
		
		dice3 = new JLabel("");
		dice3.setBounds(169, 46, 46, 42);
		contentPane.add(dice3);
		
		dice4 = new JLabel("");
		dice4.setBounds(237, 46, 46, 42);
		contentPane.add(dice4);
		
		dice5 = new JLabel("");
		dice5.setBounds(303, 46, 46, 42);
		contentPane.add(dice5);
		
		dice6 = new JLabel("");
		dice6.setBounds(372, 46, 46, 42);
		contentPane.add(dice6);
		
		currentPlayerlbl = new JLabel("");
		currentPlayerlbl.setBounds(158, 21, 125, 14);
		contentPane.add(currentPlayerlbl);
		//messy need to clean
		setVisible(true);
		if(buyIn) { buyInNewGame(); } else { newGame(); }
	}
	//Player passes Setpoints along with numdice to next player
	public void passTurn() {
		
	}
	//need 500 points in order to start adding to score
	public void buyInNewGame() {
		
	}
	//creates new game sets scores number dice also labels no 500 point buy in
	public void newGame() {
		dice = new int[6];
		diceSides = new int[6];
		numDice = 6;
		currentPlayerlbl.setText("currently " + players.get(random.nextInt(1)).getName() + "'s turn");
	}
	//rolls dice adds that dice image to label
	public void diceRoll(JLabel dieLabel) {
		int n = random.nextInt(6) + 1;
		//gets the wanted image stores it as icon then places in label
		String neededImage = "C:\\Users\\damian\\Desktop\\lab11\\dice" + n + ".png";
		dieLabel.setIcon(new ImageIcon(neededImage));
		dice[n-1]++;
	}
}
