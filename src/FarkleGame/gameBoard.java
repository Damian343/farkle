package FarkleGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.util.*;
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
	private JLabel combinationlbl, currentPlayerlbl, lblScore, farkleCountlbl, playerTotal;
	private int numDice;
	private StringBuilder possibleOpts;
	
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
		if(buyIn){ buyInNewGame(); } else { newGame(); }
	}
	//creates the gameboard where all info is displayed
	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		btnRoll.addActionListener((ActionEvent e) -> diceRoll());
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
		btnAvailableComobs.addActionListener((ActionEvent e) -> getCombos());
		btnAvailableComobs.setBounds(324, 227, 97, 23);
		contentPane.add(btnAvailableComobs);
		
		playerPicksField= new JTextField();
		playerPicksField.setBounds(128, 147, 86, 20);
		contentPane.add(playerPicksField);
		playerPicksField.setColumns(10);
		
		btnPassTurn = new JButton("Pass Turn");
		btnPassTurn.addActionListener((ActionEvent e) -> passTurn());
		btnPassTurn.setBounds(225, 227, 89, 23);
		contentPane.add(btnPassTurn);
		
		btnTakePoints = new JButton("Take points");
		btnTakePoints.addActionListener((ActionEvent e) -> getPlayerPicks(playerPicksField.getText()));
		btnTakePoints.setBounds(115, 227, 89, 23);
		contentPane.add(btnTakePoints);
		
		lblScore = new JLabel("0");
		lblScore.setBounds(125, 182, 46, 14);
		contentPane.add(lblScore);
		
		JLabel lblPlayerTotal = new JLabel("Player Total");
		lblPlayerTotal.setBounds(282, 122, 65, 14);
		contentPane.add(lblPlayerTotal);
		
		playerTotal = new JLabel("");
		playerTotal.setBounds(351, 122, 46, 14);
		contentPane.add(playerTotal);
		//messy need to clean
		setVisible(true);
	}

	public void getCombos() {
		comboSheet combos = new comboSheet();
	}
	//Pass turn when you farkle has to be different
	public void resetDice() {
		//blank out all label
		numDice = 6;
		for(int x=0; x < numDice; x++) {
			diceLbls[x].setIcon(new ImageIcon("images\\headline-lockup.png"));
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
		System.out.println("picks before anything " + playerPicks);
		List<String> picks = (Arrays.asList(playerPicks.split("\\s+")));
		List<String> opts = new LinkedList<>(Arrays.asList(possibleOpts.toString().split("\\s+")));
		
		
		if(checkPickValidity(picks, opts)) { 
			for(int x=0; x < picks.size(); x++) {
				// we have to add one because dice start at 1
				if(picks.get(x).equals("123456")){setScore += 1500;}
				if(Integer.parseInt(picks.get(x)) == 1){setScore += 100;}
				if(Integer.parseInt(picks.get(x)) == 5){setScore += 50;}
				if(picks.get(x).length() == 3){
					if(picks.get(x).substring(0, 1).equals("1")) {setScore += 300;}
					else {setScore += Integer.parseInt(picks.get(x).substring(0, 1)) * 100;}
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
			possibleOpts.setLength(0);
			playerPicks = "";
			combinationlbl.setText("");
			lblScore.setText(Integer.toString(setScore));
			playerPicksField.setText("");
		} else {
			JOptionPane.showMessageDialog(frame, "not a possible option please try again");
			playerPicksField.setText("");
		}
	}

	public boolean checkPickValidity(List<String> picks, List<String> opts) {
		int numCheck = 0;
		if(diceSides[0] == 2){ 
			opts.remove("1");
			/*
			for(int i=0; i < opts.size(); i++){
				if(Integer.parseInt(opts.get(i)) == 1){
					opts.remove(i);
					break;
				}
			}*/
		}
		if(diceSides[4] == 2){ 
			opts.remove("5");
			/*
			for(int i=0; i < opts.size(); i++){
				if(Integer.parseInt(opts.get(i)) == 5){
					opts.remove();
					break;
				}
			}*/
		}
		System.out.println(picks);
		for(int x=0; x < picks.size(); x++) {
			for(int y=0; y < opts.size(); y++){
				if(Integer.parseInt(picks.get(x)) == Integer.parseInt(opts.get(y))) { numCheck++; }
			}
		}
        return numCheck == picks.size();
	}
	//removes total dice from players picks and checks to see if out of dice
	public void diceRemover(int amount) {
		if((numDice-amount) > 0){
			numDice = numDice - amount;
			for(int n=numDice; n < 6; n++) {
				diceLbls[n].setIcon(new ImageIcon("src\\images\\headline-lockup.png"));
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Cleared All Dice! roll all again \n current set score: " + setScore);
			numDice = 6;
			resetDice();
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
				pairs.concat(Integer.toString(x+1)+Integer.toString(x+1));
			}
		}
		if(pairCount == 3){ possibleOpts.append(pairs+" "); setScore += 1500; }
	}
	//checks to see if two triplets
	public void twoTripletCheck(){
		int tripCount=0;
		for(int n=0; n < numDice; n++) {
			//if 3 of one number add to possible opts
			if (diceSides[n] == 3) { tripCount++; possibleOpts.append(Integer.toString(n)+Integer.toString(n)+Integer.toString(n));}
		}
		if(tripCount==2) { possibleOpts.append(" "); setScore += 2500; }
		else { possibleOpts.setLength(0); }
	}
	//gets possible options available
	public void checkNumbers() {
		String nums="";
		//gets rid of redundency only runs if 6 dice available
		if(numDice == 6){
			pairCheck();
			twoTripletCheck();
			if(straightCheck()){ possibleOpts.append("123456 "); }
		}
		//checks for triplets, quads, fives and sixes 
		for(int x=0; x < numDice; x++) {
				// we have to add one because dice start at 1
				nums = Integer.toString(x + 1);
				if(dice[x] == 1) {possibleOpts.append("1 ");}
				if(dice[x] == 5) {possibleOpts.append("5 ");}
				if(diceSides[x] == 3){possibleOpts.append(nums+nums+nums+" ");}
				//add 4 and pair combo
				if(diceSides[x] == 4) {possibleOpts.append(nums+nums+nums+nums+" ");}
				if(diceSides[x] == 5) {possibleOpts.append(nums+nums+nums+nums+nums+" ");}
				if(diceSides[x] == 6) {possibleOpts.append(nums+nums+nums+nums+nums+nums+" ");}
		}
	}
	/*NEED TO FINISH*/
	public void buyInNewGame() {
		dice = new int[6];
		diceSides = new int[6];
		numDice = 6;
		btnPassTurn.setVisible(false);
		btnTakePoints.setVisible(false);
		boolean under500 = true;
		while(under500) {
			
		}
	}
	//no possible combinations was acquired
	public void farkled() {
		players.get(currentPlayer).incrementFarkle();
		JOptionPane.showMessageDialog(frame, "You Farkled!" );
		
		if (players.get(currentPlayer).getFarkleCount() == 3) { /*3 farkles reset score completely no buy back in */ 
			players.get(currentPlayer).setScore(0);
			players.get(currentPlayer).resetFarkle();
		}
		
		 playerChanger();
		 //change buttons
		 btnRoll.setVisible(true);
		 btnTakePoints.setVisible(false);
		 combinationlbl.setText("");
		 currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
		 playerTotal.setText(Integer.toString(players.get(currentPlayer).getScore()));
		 setScore=0;
		 lblScore.setText(Integer.toString(setScore));
		 farkleCountlbl.setText(Integer.toString(players.get(currentPlayer).getFarkleCount()));
		 resetDice();
	}
	//creates new game sets scores number dice also labels no 500 point buy in
	public void newGame() {
		dice = new int[6];
		diceSides = new int[6];
		numDice = 6;
		btnPassTurn.setVisible(false);
		btnTakePoints.setVisible(false);
		currentPlayer = (Math.random()<0.5)?0:1;
		currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
		farkleCountlbl.setText("0");
	}
	//sets diceSides and dice and images, checks number and sets options label
	public void diceRoll() {
		Arrays.fill(diceSides, 0); Arrays.fill(dice, 0);
		int n;
		String neededImage;
		for(int x=0; x < numDice; x++) {
			n = random.nextInt(6) + 1;
			neededImage = "src\\images\\dice" + n + ".png";
			ImageIcon im = new ImageIcon(neededImage);
			diceLbls[x].setIcon(im);
			System.out.println(diceLbls[x].getIcon());
			dice[x] = n;
			diceSides[n-1]++;
		}
		System.out.println(Arrays.toString(dice));
		System.out.println(Arrays.toString(diceSides));
		checkNumbers();
		//see if there is any combo
		if(possibleOpts.toString().equals("")) { farkled(); }
		else {
			combinationlbl.setText(possibleOpts.toString());
			btnRoll.setVisible(false);
			btnTakePoints.setVisible(true);
			btnPassTurn.setVisible(false);
		}
	}
	
	public void passTurn() {
		//see if player wants to continue 
		int n = JOptionPane.showOptionDialog(frame, "Would you like to Continue from previous Player(Points/num dice)", "Pass Turn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		System.out.println(n);
		players.get(currentPlayer).resetFarkle();
		//NEED TO CHANGE PLAYER CLASS TO ALLOW += SCORE//
		players.get(currentPlayer).setScore(setScore);
		if(players.get(currentPlayer).getScore() >= 10000) { endGame(); }
		if(n == 0){
			playerChanger();
			farkleCountlbl.setText(Integer.toString(players.get(currentPlayer).getFarkleCount()));
			currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
			playerTotal.setText(Integer.toString(players.get(currentPlayer).getScore()));
			possibleOpts.setLength(0);
		} else { /*need to keep score for next player and do not change number of dice*/
			playerChanger();
			combinationlbl.setText("");
			currentPlayerlbl.setText("currently " + players.get(currentPlayer).getName() + "'s turn");
			playerTotal.setText(Integer.toString(players.get(currentPlayer).getScore()));
			setScore=0;
			farkleCountlbl.setText(Integer.toString((players.get(currentPlayer).getFarkleCount())));
			lblScore.setText(Integer.toString(setScore));
			resetDice();
		}
	}

	public void endGame() {
		JOptionPane.showMessageDialog(frame, "Congratulations, " + players.get(currentPlayer).getName() + " You have won!\n"
				+ "with a total score of " + players.get(currentPlayer).getScore() + " points");
		resetGame();
	}
	//call when player wins to reset
	public void resetGame(){
		Arrays.fill(diceSides, 0); Arrays.fill(dice, 0);
		
	}
}
