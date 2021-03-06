package FarkleGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

//each player class keeps track of score, random player is selected to go first
class GameBoard extends JFrame {
	//gui elements
	private final JFrame frame = new JFrame();
    private JTextField playerPicksField;
	private JButton btnPassTurn, btnRoll, btnTakePoints;
	private JLabel combinationlbl, curPlayerlbl, lblScore, farkleCountlbl, playerTotal;
	private JLabel[] diceLbls;
	//dice elements
	private int[] diceSides;
	private int[] dice;
	private int numDice;
	//player elements
	private final ArrayList<Player> players = new ArrayList<Player>();
	private Player player;
	private int curSetScore;
	private int playerIndex;
    private Random random;
	//combochecker elements
	private StringBuilder possibleOpts;
	
	//called from main menu, creates the players, starts game
    GameBoard() {
		setTitle("Farkle");
		players.add(new Player(nameOne));
		players.add(new Player(nameTwo));
		curSetScore = 0;
        boolean buyIn1 = buyIn;
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

		Font font = new Font(Font.MONOSPACED, Font.BOLD, 16);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
			sets positions and initial text of labels
		*/
		curPlayerlbl = new JLabel("");
		curPlayerlbl.setFont(font);
		curPlayerlbl.setBounds(10, 15, 500, 20);
		contentPane.add(curPlayerlbl);

		JLabel lblAvailableCombinations = new JLabel("Available Combinations: ");
		lblAvailableCombinations.setBounds(10, 122, 119, 14);
		contentPane.add(lblAvailableCombinations);

		playerPicksField= new JTextField();
		playerPicksField.setBounds(128, 147, 86, 20);
		contentPane.add(playerPicksField);
		playerPicksField.setColumns(10);

		JLabel playerScorelbl = new JLabel("Players Score");
		playerScorelbl.setBounds(50, 182, 65, 14);
		contentPane.add(playerScorelbl);

		JLabel lblPleseSelectCombinations = new JLabel("Dice Selection: ");
		lblPleseSelectCombinations.setBounds(50, 150, 89, 14);
		contentPane.add(lblPleseSelectCombinations);

		combinationlbl = new JLabel("");
		combinationlbl.setBounds(158, 122, 124, 20 );
		contentPane.add(combinationlbl);

		lblScore = new JLabel("0");
		lblScore.setBounds(125, 182, 46, 14);
		contentPane.add(lblScore);

		JLabel lblFarkleCount = new JLabel("Farkle Count");
		lblFarkleCount.setBounds(282, 182, 71, 14);
		contentPane.add(lblFarkleCount);

		farkleCountlbl = new JLabel("");
		farkleCountlbl.setBounds(351, 182, 46, 14);
		contentPane.add(farkleCountlbl);

		playerTotal = new JLabel("0");
		playerTotal.setBounds(351, 122, 46, 14);
		contentPane.add(playerTotal);

		JLabel lblPlayerTotal = new JLabel("Player Total");
		lblPlayerTotal.setBounds(282, 122, 65, 14);
		contentPane.add(lblPlayerTotal);

		/*
			sets button positions and actions
		*/
		btnRoll = new JButton("Roll");
		btnRoll.addActionListener((ActionEvent e) -> diceRoll());
		btnRoll.setBounds(10, 227, 89, 23);
		contentPane.add(btnRoll);

		btnTakePoints = new JButton("Take points");
		btnTakePoints.addActionListener((ActionEvent e) -> {
			String text = playerPicksField.getText();
			if(text.equals("")) return;
			else getPlayerPicks(text);
		});
		btnTakePoints.setBounds(115, 227, 89, 23);
		contentPane.add(btnTakePoints);

		btnPassTurn = new JButton("Pass Turn");
		btnPassTurn.addActionListener((ActionEvent e) -> passTurn());
		btnPassTurn.setBounds(225, 227, 89, 23);
		contentPane.add(btnPassTurn);
		//not used in any other functions no need to be global
		JButton btnAvailableComobs = new JButton("Combinations");
		btnAvailableComobs.addActionListener((ActionEvent e) -> getCombos());
		btnAvailableComobs.setBounds(324, 227, 97, 23);
		contentPane.add(btnAvailableComobs);
		/*
			sets the dice positions and adds to frame
		*/
		diceLbls = new JLabel[6];

		diceLbls[0] = new JLabel("");
		diceLbls[0].setBounds(20, 46, 46, 42);
		contentPane.add(diceLbls[0]);

		diceLbls[1] = new JLabel("");
		diceLbls[1].setBounds(92, 46, 46, 42);
		contentPane.add(diceLbls[1]);

		diceLbls[2] = new JLabel("");
		diceLbls[2].setBounds(164, 46, 46, 42);
		contentPane.add(diceLbls[2]);

		diceLbls[3] = new JLabel("");
		diceLbls[3].setBounds(236, 46, 46, 42);
		contentPane.add(diceLbls[3]);

		diceLbls[4] = new JLabel("");
		diceLbls[4].setBounds(308, 46, 46, 42);
		contentPane.add(diceLbls[4]);

		diceLbls[5] = new JLabel("");
		diceLbls[5].setBounds(380, 46, 46, 42);
		contentPane.add(diceLbls[5]);

		setVisible(true);
		resetDice();
	}

	private void getCombos() {
		comboSheet combos = new comboSheet();
	}
	//Pass turn when you farkle has to be different
    public void resetDice() {
		//blank out all label
		numDice = diceLbls.length;
		for(int x=0; x < numDice; x++) {
			diceLbls[x].setIcon(new ImageIcon("src\\images\\dice1.png"));
		}
	}

	private void playerChanger() {
    	// make current player an actual player
		playerIndex++;
		if(playerIndex == players.size()){
			playerIndex = 0;
		}
		player = players.get(playerIndex);
	}
	//checks players numbers adds to set score
    private void getPlayerPicks(String playerPicks) {
		System.out.println("picks before anything " + playerPicks);
		List<String> picks = (Arrays.asList(playerPicks.split("\\s+")));
		List<String> opts = new LinkedList<>(Arrays.asList(possibleOpts.toString().split("\\s+")));
		
		/*
		need to add check for the possible 6 combo banger
		possible 6 are
			six of kind  = 3000 points
			1-6 straight = 1500 points
			three pairs  = 1500 points
			four + pair  = 1500 points
			two trips    = 2500 points
		need to realize what points to be awarded
		the possible options number determines somewhat
		of the players score
		 */
		if(checkPickValidity(picks, opts)) {
			/*
			have to take in to consideration
			that picks will not have the number of
			good numbers but the total options
			so good approach is to check if
			the number of dice is equal
			to six then check if the total possible
			options is one we then know
			this option is a six dice roll
			possiblity
			 */
            for (String pick : picks) {
                // we have to add one because dice start at 1
                if (pick.equals("123456")) {
                    player.increaseScore(1500);
                }
                if(dice.getNumDice() == 6 && checker.getPossibleOpts().length == 1){
					if(checker.twoTripletCheck(dice.getDiceSides())){
						player.increaseScore(2500);
					} else {
						player.increaseScore(1500);
					}
				}
                if (Integer.parseInt(pick) == 1) {
					player.increaseScore(100);
                }
                if (Integer.parseInt(pick) == 5) {
					player.increaseScore(50);
                }
                if (pick.length() == 3) {
                    if (pick.substring(0, 1).equals("1")) {
						player.increaseScore(300);
                    } else {
						player.increaseScore(Integer.parseInt(pick.substring(0, 1)) * 100);
                    }
                }
                if (pick.length() == 4) {
					player.increaseScore(1000);
                }
                if (pick.length() == 5) {
					player.increaseScore(2000);
                }
                if (pick.length() == 6) {
					player.increaseScore(3000);
                }
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
			combinationlbl.setText("");
			lblScore.setText(Integer.toString(player.getSetScore()));
			playerPicksField.setText("");
		} else {
			JOptionPane.showMessageDialog(frame, "not a possible option please try again");
			playerPicksField.setText("");
		}
	}

	private boolean checkPickValidity(List<String> picks, List<String> opts) {
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
        for (String pick : picks) {
            for (String opt : opts) {
                if (Integer.parseInt(pick) == Integer.parseInt(opt)) {
                    numCheck++;
                }
            }
        }
        return numCheck == picks.size();
	}
	//removes total dice from players picks and checks to see if out of dice
    private void diceRemover(int amount) {
		if((numDice-amount) > 0){
			numDice = numDice - amount;
			for(int n=numDice; n < 6; n++) {
				diceLbls[n].setIcon(new ImageIcon("src\\images\\headline-lockup.png"));
			}
		} else {
			JOptionPane.showMessageDialog(frame,
					"Cleared All Dice! roll all again \n current set score: " + player.getSetScore()
			);
			numDice = 6;
			resetDice();
		}
	}
	//checks to see if there is a straight available
    private boolean straightCheck(){
		for(int x=0; x<6; x++){
			if(diceSides[x] != 1){
				return false;
			}
		}
		return true;
	}
	//checks if three pairs available
    private void pairCheck() {
		int pairCount=0;
		String pairs="";
		
		for(int x=0; x<6; x++) {
			if(diceSides[x] == 2){
				pairCount++;
				pairs += Integer.toString(x+1)+Integer.toString(x+1);
			}
		}
		System.out.println(pairs);
		if(pairCount == 3){ possibleOpts.append(pairs).append(" "); player.increaseScore(1500); }
	}
	//checks to see if two triplets
    private void twoTripletCheck(){
		int tripCount=0;
		for(int n=0; n < numDice; n++) {
			//if 3 of one number add to possible opts
			String num = Integer.toString(n);
			if (diceSides[n] == 3) { tripCount++; possibleOpts.append(num).append(num).append(num);}
		}
		if(tripCount==2) { possibleOpts.append(" "); curSetScore += 2500; }
		else { possibleOpts = new StringBuilder(); }
	}
	//gets possible options available
    private void checkNumbers() {
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
				String currString = "";
				if(dice[x] == 1) {possibleOpts.append("1 ");}
				if(dice[x] == 5) {possibleOpts.append("5 ");}
				if(diceSides[x] == 3){
				    currString = nums+nums+nums+" ";
				    possibleOpts.append(currString);
				}
				//add 4 and pair combo
				if(diceSides[x] == 4) {
                    currString = nums+nums+nums+nums+" ";
                    possibleOpts.append(currString);
				}
				if(diceSides[x] == 5) {
				    currString = nums+nums+nums+nums+nums+" ";
				    possibleOpts.append(currString);
				}
				if(diceSides[x] == 6) {
				    currString = nums+nums+nums+nums+nums+nums+" ";
				    possibleOpts.append(currString);
				}
		}
	}
	/*NEED TO FINISH*/
    private void buyInNewGame() {
		dice = new int[6];
		diceSides = new int[6];
		numDice = 6;
		btnPassTurn.setVisible(false);
		btnTakePoints.setVisible(false);
		boolean under500 = true;
		while(under500) {
			
		}
	}
	//no possible combinations were acquired
    private void farkled() {
		player.incrementFarkle();
		JOptionPane.showMessageDialog(frame, "You Farkled!" );
		
		if (player.getFarkleCount() == 3) { /*3 farkles reset score completely no buy back in */
			player.setScore(0);
			player.resetFarkle();
		}
		
		 playerChanger();
		 //change buttons
		 btnRoll.setVisible(true);
		 btnTakePoints.setVisible(false);
		 combinationlbl.setText("");
		 curPlayerlbl.setText("currently " + player.getName() + "'s turn");
		 playerTotal.setText(Integer.toString(player.getScore()));
		 curSetScore = 0;
		 lblScore.setText(Integer.toString(player.getSetScore()));
		 farkleCountlbl.setText(Integer.toString(player.getFarkleCount()));
		 resetDice();
	}
	//creates new game sets scores number dice also labels no 500 point buy in
    private void newGame() {
		dice = new int[6];
		diceSides = new int[6];
		numDice = 6;
		playerIndex = random.nextInt(players.size());
		player = players.get(playerIndex);
		btnPassTurn.setVisible(false);
		btnTakePoints.setVisible(false);
		curPlayerlbl.setText("currently " + player.getName() + "'s turn");
		farkleCountlbl.setText("0");
	}
	//sets diceSides and dice and images, checks number and sets options label
    private void diceRoll() {
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

	public int turnAlert(String name) {
		int n = JOptionPane.showOptionDialog(frame,
				"Would " + name + " like to Continue from previous Player(Points/num dice)",
				"Pass Turn",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, null, null);
		return n;
	}

	public void playerTakesDice(Player player) {
		combinationlbl.setText("");
		curPlayerlbl.setText("currently " + player.getName() + "'s turn");
		playerTotal.setText(Integer.toString(player.getScore()));
		farkleCountlbl.setText(Integer.toString((player.getFarkleCount())));
		lblScore.setText(Integer.toString(player.getSetScore()));
	}

	public void playerDoesntTakeDice(Player player) {
		farkleCountlbl.setText(Integer.toString(player.getFarkleCount()));
		curPlayerlbl.setText("currently " + player.getName() + "'s turn");
		playerTotal.setText(Integer.toString(player.getScore()));
	}
	
	private void passTurn() {
		//see if player wants to continue 

		player.resetFarkle();
		player.resetSetScore();
		player.increaseScore(curSetScore);

		if(player.getScore() >= 10000) { endGame(); }
		if(n == 0){
			playerChanger();
			farkleCountlbl.setText(Integer.toString(player.getFarkleCount()));
			curPlayerlbl.setText("currently " + player.getName() + "'s turn");
			playerTotal.setText(Integer.toString(player.getScore()));
			possibleOpts.setLength(0);
		} else { /*need to keep score for next player and do not change number of dice*/
			int tempSet = player.getSetScore();
			player.resetSetScore();

			playerChanger();

			player.changeSetScore(tempSet);
			combinationlbl.setText("");
			curPlayerlbl.setText("currently " + player.getName() + "'s turn");
			playerTotal.setText(Integer.toString(player.getScore()));
			curSetScore = 0;
			farkleCountlbl.setText(Integer.toString((player.getFarkleCount())));
			lblScore.setText(Integer.toString(player.getSetScore()));
			resetDice();
		}
	}

	private void endGame() {
		JOptionPane.showMessageDialog(frame, "Congratulations, " + player.getName() + " You have won!\n"
				+ "with a total score of " + player.getScore() + " points");
		resetGame();
	}
	//call when player wins to reset
    public void resetGame(String name){
    	resetDice();
		playerTotal.setText("0");
		farkleCountlbl.setText("0");
		curPlayerlbl.setText("Currently " + name + "'s turn.");
	}
}
