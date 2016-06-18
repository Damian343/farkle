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
	private int setScore;
	private JLabel combinationlbl, currentPlayerlbl, lblScore, farkleCountlbl;
	private int numDice;
	private String possibleOpts="";
	
	//called from main menu, creates the players, starts game
	public gameBoard(String nameOne, String nameTwo, boolean buyIn) {
		setTitle("Farkle");
		players.add(new Player(nameOne));
		players.add(new Player(nameTwo));
		setScore = 0;
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
		playerScorelbl.setBounds(50, 182, 65, 14);
		contentPane.add(playerScorelbl);
		
		JLabel lblAvailableCombinations = new JLabel("Available Combinations: ");
		lblAvailableCombinations.setBounds(10, 122, 119, 14);
		contentPane.add(lblAvailableCombinations);
		
		combinationlbl = new JLabel("");
		combinationlbl.setBounds(158, 122, 124, 14);
		contentPane.add(combinationlbl);
		
		JLabel lblPleseSelectCombinations = new JLabel("Plese select dice");
		lblPleseSelectCombinations.setBounds(40, 147, 89, 14);
		contentPane.add(lblPleseSelectCombinations);
		
		JLabel lblFarkleCount = new JLabel("Farkle Count");
		lblFarkleCount.setBounds(282, 182, 71, 14);
		contentPane.add(lblFarkleCount);
		
		farkleCountlbl = new JLabel("");
		farkleCountlbl.setBounds(351, 182, 46, 14);
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
		playerPicksField.setBounds(128, 147, 86, 20);
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
		lblScore.setBounds(125, 182, 46, 14);
		contentPane.add(lblScore);
		//messy need to clean
		setVisible(true);
	}
//Pass turn when you farkle has to be different
	public void resetDice() {
		//blank out all label
		numDice = 6;
		for(int x=0; x < numDice; x++) {
			diceLbls[x].setIcon(new ImageIcon("C:\\Users\\damian\\Desktop\\lab11\\dice1.png"));
		}
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
			if(picks.get(x)=="123456"){setScore += 1500;}
			if(Integer.parseInt(picks.get(x)) == 1){setScore += 100;}
			if(Integer.parseInt(picks.get(x)) == 5){setScore += 50;}
			if(picks.get(x).length() == 3){
				if(Integer.parseInt(picks.get(x).indexOf(0)) == 1) { setScore += 300; }
				else {setScore += (Integer.parseInt(picks.get(x).indexOf(0))) * 100;}
			}
			if(picks.get(x).length() == 4){setScore += 1000;}
			if(picks.get(x).length() == 5){setScore += 2000;}
			if(picks.get(x).length() == 6){setScore += 3000;}
		}
		//have to turn off certain buttons not allowed to use
		btnRoll.setVisible(true);
		btnPassTurn.setVisible(true);
		btnTakePoints.setVisible(false);
		//passes how many dice are getting rid of
		playerPicks = playerPicks.replaceAll("\\s","");
		diceRemover(playerPicks.length());
		//set point labels
		combinationlbl.setText(" ");
		lblScore.setText(Integer.toString(setScore));
		playerPicksField.setText(" ");
		//set numdice
		
	}
	//removes total dice from players picks and checks to see if out of dice
	public void diceRemover(int amount) {
		if((numDice-amount) > 0){
			numDice = numDice - amount;
			for(int n=numDice; n >= 0; n--) {
				diceLbls[n].setIcon(new ImageIcon("C:\\Uesrs\\damian\\Desktop\\lab11\\dice1.png"));
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Cleared All Dice! roll all again \n current set score: " + setScore);
			numDice = 6;
			resetDice();
		}
		possibleOpts="";
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
		if(pairCount == 3){ possibleOpts += pairs+", "; setScore += 1500; }
	}
	//checks to see if two triplets
	public void twoTripletCheck(){
		int tripCount=0;
		for(int n=0; n < numDice; n++) {
			//if 3 of one number add to possible opts
			if (diceSides[n] == 3) { tripCount++; possibleOpts += Integer.toString(n)+Integer.toString(n)+Integer.toString(n);}
		}
		if(tripCount==2) { possibleOpts += ", "; setScore += 2500; }
		else { possibleOpts = " "; } 
	}
	//gets possible options available
	public void checkNumbers() {
		String nums="";
		//gets rid of redundency only runs if 6 dice available
		if(numDice == 6){
			pairCheck();
			twoTripletCheck();
			if(straightCheck()){ possibleOpts += "123456"; } 
		} else {
		//checks for triplets, quads, fives and sixes 
			for(int x=0; x < numDice; x++) {
				// we have to add one because dice start at 1
				nums = Integer.toString(x + 1);
				if(dice[x] == 1) {possibleOpts+= " 1, ";}
				if(dice[x] == 5) {possibleOpts += " 5, ";}
				if(diceSides[x] == 3){possibleOpts += nums+nums+nums+", ";}
				//add 4 and pair combo
				if(diceSides[x] == 4) {possibleOpts += nums+nums+nums+nums+", ";}
				if(diceSides[x] == 5) {possibleOpts += nums+nums+nums+nums+nums+", ";}
				if(diceSides[x] == 6) {possibleOpts += nums+nums+nums+nums+nums+nums+", ";}
			}
		}
		if(possibleOpts == " ") { farkled(); }
	}
	//need 500 points in order to start adding to score
	public void buyInNewGame() {
		
	}
	//no possible combinations was acquired
	public void farkled() {
		players.get(currentPlayer).incrementFarkle();
		if(players.get(currentPlayer).getFarkleCount() == 3) { /*3 farkles reset score completely no buy back in */ 
			
			players.get(currentPlayer).setScore(0);
			players.get(currentPlayer).resetFarkle();
			
		}
			 //basically a pass turn with no context dialog box
		 playerChanger();
		 combinationlbl.setText("");
		 currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
		 setScore=0;
		 lblScore.setText(Integer.toString(setScore));
		 farkleCountlbl.setText(parseInt(players.get(currentPlayer).getFarkleCount()));
		 resetDice();
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
		farkleCountlbl.setText("0");
	}
	//sets diceSides and dice and images, checks number and sets options label
	public void diceRoll() {
		Arrays.fill(diceSides, 0); Arrays.fill(dice, 0);
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

	public void passTurn() {
		//see if player wants to continue 
		int n = JOptionPane.showOptionDialog(frame, "Would you like to Continue from previous Player(Points/num dice)", "Pass Turn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		players.get(currentPlayer).resetFarkle();
		//NEED TO CHANGE PLAYER CLASS TO ALLOW += SCORE//
		players.get(currentPlayer).setScore(setScore);
		
		if(n == 0){
			playerChanger();
			combinationlbl.setText("");
			currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
			setScore=0;
			farkleCountlbl.setText(parseInt(players.get(currentPlayer).getFarkleCount()));
			lblScore.setText(Integer.toString(setScore));
			resetDice();
		} else { /*need to keep score for next player and do not change number of dice*/
			playerChanger();
			farkleCountlbl.setText(parseInt(players.get(currentPlayer).getFarkleCount()));
			currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
			possibleOpts="";
		}
	}
}
