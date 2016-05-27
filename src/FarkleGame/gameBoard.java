package FarkleGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
//each player class keeps track of score, random player is selected to go first
public class gameBoard extends JFrame {
	private ArrayList<Player> players = new ArrayList<Player>();
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField playerPicksField;
	private int[] diceSides;
	private int[] dice;
	private int currentPlayer;
	private boolean buyIn;
	private Random random;
	private JButton btnPassTurn, btnRoll, btnTakePoints;
	private JLabel[] diceLbls;
	private JLabel combinationlbl, currentPlayerlbl, lblScore;
	private int numDice;
	private String possibleOpts="";
	
	//called from main menu, creates the players, starts game
	public gameBoard(String nameOne, String nameTwo, boolean buyIn) {
		setTitle("Farkle");
		players.add(new Player(nameOne));
		players.add(new Player(nameTwo));
		this.buyIn = buyIn;
		random = new Random(); 
		createWindow();
		//figure out wether need 500 points to start
		if(buyIn) { buyInNewGame(); } else { newGame(); }
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
		playerScorelbl.setBounds(292, 122, 65, 14);
		contentPane.add(playerScorelbl);
		
		JLabel lblAvailableCombinations = new JLabel("Available Combinations: ");
		lblAvailableCombinations.setBounds(10, 122, 119, 14);
		contentPane.add(lblAvailableCombinations);
		
		combinationlbl = new JLabel("");
		combinationlbl.setBounds(158, 122, 124, 14);
		contentPane.add(combinationlbl);
		
		JLabel lblAvailablePointsIn = new JLabel("Total Roll Score: ");
		lblAvailablePointsIn.setBounds(40, 160, 89, 14);
		contentPane.add(lblAvailablePointsIn);
		
		JLabel rollPointslbl = new JLabel("");
		rollPointslbl.setBounds(158, 160, 76, 14);
		contentPane.add(rollPointslbl);
		
		JLabel lblPleseSelectCombinations = new JLabel("Plese select dice");
		lblPleseSelectCombinations.setBounds(38, 197, 89, 14);
		contentPane.add(lblPleseSelectCombinations);
		
		JLabel lblFarkleCount = new JLabel("Farkle Count");
		lblFarkleCount.setBounds(292, 197, 71, 14);
		contentPane.add(lblFarkleCount);
		
		JLabel farkleCountlbl = new JLabel("");
		farkleCountlbl.setBounds(360, 197, 46, 14);
		contentPane.add(farkleCountlbl);
		
		btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diceRoll();
			}
		});
		btnRoll.setBounds(10, 227, 89, 23);
		contentPane.add(btnRoll);
		
		diceLbls = new JLabel[6];
		diceLbls[0] = new JLabel("");
		diceLbls[0].setBounds(24, 46, 46, 42);
		contentPane.add(diceLbls[0]);
		
		diceLbls[1] = new JLabel("");
		diceLbls[1].setBounds(97, 46, 46, 42);
		contentPane.add(diceLbls[1]);
		
		diceLbls[2] = new JLabel("");
		diceLbls[2].setBounds(169, 46, 46, 42);
		contentPane.add(diceLbls[2]);
		
		diceLbls[3] = new JLabel("");
		diceLbls[3].setBounds(237, 46, 46, 42);
		contentPane.add(diceLbls[3]);
		
		diceLbls[4] = new JLabel("");
		diceLbls[4].setBounds(303, 46, 46, 42);
		contentPane.add(diceLbls[4]);
		
		diceLbls[5] = new JLabel("");
		diceLbls[5].setBounds(372, 46, 46, 42);
		contentPane.add(diceLbls[5]);
		
		currentPlayerlbl = new JLabel("");
		currentPlayerlbl.setBounds(158, 21, 125, 14);
		contentPane.add(currentPlayerlbl);
		
		JButton btnAvailableComobs = new JButton("Combinations");
		btnAvailableComobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboSheet combos = new comboSheet();
			}
		});
		btnAvailableComobs.setBounds(324, 227, 97, 23);
		contentPane.add(btnAvailableComobs);
		
		playerPicksField= new JTextField();
		playerPicksField.setBounds(148, 194, 86, 20);
		contentPane.add(playerPicksField);
		playerPicksField.setColumns(10);
		
		btnPassTurn = new JButton("Pass Turn");
		btnPassTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passTurn();
			}
		});
		btnPassTurn.setBounds(225, 227, 89, 23);
		contentPane.add(btnPassTurn);
		
		btnTakePoints = new JButton("Take points");
		btnTakePoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getPlayerPicks(playerPicksField.getText());
			}
		});
		btnTakePoints.setBounds(115, 227, 89, 23);
		contentPane.add(btnTakePoints);
		
		lblScore = new JLabel("0");
		lblScore.setBounds(367, 122, 46, 14);
		contentPane.add(lblScore);
		//messy need to clean
		setVisible(true);
	}
	public void passTurn() {
		playerChanger();
		combinationlbl.setText("");
		currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
		lblScore.setText(Integer.toString(players.get(currentPlayer).getSetScore()));
		resetDice();
	}
	public void resetDice() {
		//blank out all labels
	}
	public void playerChanger() {
		if(currentPlayer == 0){
			currentPlayer++;
		} else {
			currentPlayer--;
		}
	}
	//checks players numbers adds to set score
	public void getPlayerPicks(String playerPicks) {
		List<String> picks = (Arrays.asList(playerPicks.split(" ")));
		for(int x=0; x < picks.size(); x++) {
			// we have to add one because dice start at 1
			/** HAVE TO FIX BECAUSE TAKES ONLY LENGTH AND NO CHECK **/
			if(picks.get(x)=="123456"){players.get(currentPlayer).setSetScore(1500);}
			if(Integer.parseInt(picks.get(x)) == 1){players.get(currentPlayer).setSetScore(100);}
			if(Integer.parseInt(picks.get(x)) == 5){players.get(currentPlayer).setSetScore(50);}
			if(picks.get(x).length() == 3){players.get(currentPlayer).setSetScore((Integer.parseInt(picks.get(x)))* 100); }
			if(picks.get(x).length() == 4){players.get(currentPlayer).setSetScore(1000);}
			if(picks.get(x).length() == 5){players.get(currentPlayer).setSetScore(2000);}
			if(picks.get(x).length() == 6){players.get(currentPlayer).setSetScore(3000);}
		}
		//have to turn off certain buttons not allowed to use
		btnRoll.setVisible(true);
		btnPassTurn.setVisible(true);
		btnTakePoints.setVisible(false);
		//passes how many dice are getting rid of
		playerPicks = playerPicks.replaceAll("\\s","");
		diceRemover(playerPicks.length());
		//set point labels
		combinationlbl.setText("");
		lblScore.setText(Integer.toString(players.get(currentPlayer).getSetScore()));
		
	}
	//removes total dice from players picks and checks to see if out of dice
	public void diceRemover(int amount) {
		if((numDice-amount) > 0){
			numDice = numDice - amount;
		} else {
			JOptionPane.showMessageDialog(frame, "Cleared All Dice! roll all 6 again");
			numDice = 6;
		}
	}
	//checks to see if theres a straight available
	public boolean straightCheck(){
		for(int x=0; x<6; x++){
			if(diceSides[x] != 1){
				return false;
			}
		}
		return true;
	}
	//checks if three pairs available
	public void pairCheck() {
		int pairCount=0;
		String pairs="";
		
		for(int x=0; x<6; x++) {
			if(diceSides[x] == 2){
				pairCount++;
				pairs+=Integer.toString(x+1)+Integer.toString(x+1);
			}
		}
		if(pairCount == 3){ possibleOpts += pairs+", "; }
	}
	//checks to see if two triplets
	public void twoTripletCheck(){
		for(int n=0; n < numDice; n++) {
			//if 3 of one number add to possible opts
			if (diceSides[n] == 3) { possibleOpts += Integer.toString(n)+Integer.toString(n)+Integer.toString(n); }
		}
	}
	//gets possible options available
	public void checkNumbers() {
		String nums="";
		//gets rid of redundency only runs if 6 dice available
		if(numDice == 6){
			pairCheck();
			twoTripletCheck();
			if(straightCheck()){ possibleOpts += "123456"; } 
		}
		//checks for triplets, quads, fives and sixes 
		for(int x=0; x < numDice; x++) {
			// we have to add one because dice start at 1
			nums = Integer.toString(x + 1);
			if(dice[x] == 1) {possibleOpts+= " 1, ";}
			if(dice[x] == 5) {possibleOpts += " 5, ";}
			if(diceSides[x] == 3){possibleOpts += nums+nums+nums+", ";}
			if(diceSides[x] == 4) {possibleOpts += nums+nums+nums+nums+", ";}
			if(diceSides[x] == 5) {possibleOpts += nums+nums+nums+nums+nums+", ";}
			if(diceSides[x] == 6) {possibleOpts += nums+nums+nums+nums+nums+nums+", ";}
		}
	}
	//need 500 points in order to start adding to score
	public void buyInNewGame() {
		
	}
	//no possible combinations was acquired
	public void farkled() {
		
	}
	//creates new game sets scores number dice also labels no 500 point buy in
	public void newGame() {
		dice = new int[6];
		diceSides = new int[6];
		numDice = 6;
		btnPassTurn.setVisible(false);
		btnTakePoints.setVisible(false);
		currentPlayer = random.nextInt(1);
		currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
	}
	//sets diceSides and dice and images, checks number and sets options label
	public void diceRoll() {
		int n=0;
		String neededImage="";
		for(int x=0; x < numDice; x++) {
			n = random.nextInt(6) + 1;
			neededImage = "C:\\Users\\damian\\Desktop\\lab11\\dice" + n + ".png";
			diceLbls[x].setIcon(new ImageIcon(neededImage));
			dice[x] = n;
			diceSides[n-1]++;
		}
		System.out.println(Arrays.toString(dice));
		System.out.println(Arrays.toString(diceSides));
		checkNumbers();
		combinationlbl.setText(possibleOpts);
		btnRoll.setVisible(false);
		btnTakePoints.setVisible(true);
		btnPassTurn.setVisible(false);
	}
}
