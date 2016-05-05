package FarkleGame;
import java.lang.reflect.Array;
import java.util.*;

public class FarkleGame {
	static int[] dice;
	static int[] diceSides; //how many of each side is rolled 
	static int numDice = 6;
	int score = 0;		//total points acquired in total by player 
	static int totalScore = 0; //amount of points in  current "set" 
	int possibleScore = 0; //total points possible in current "roll"
	String possibleOpts = ""; //possible number combinations
	static String playerPicks = "";
	List<String> playerNumbers;
	static boolean playerCheck = false;
	static int farkleCounter = 0;
	static int pairCount = 0;

	public FarkleGame() {
		dice = new int[6];
		diceSides = new int[6];
	}
	
	public void quickSort(int low, int high) {
		if (dice == null || dice.length == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = dice[middle];
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (dice[i] < pivot) {
				i++;
			}
 
			while (dice[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = dice[i];
				dice[i] = dice[j];
				dice[j] = temp;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(low, j);
 
		if (high > i)
			quickSort(i, high);
	}
	
	public void print() {
		System.out.print("| ");
		for(int i=0; i < numDice; i++) {
			System.out.print(dice[i] + " | ");
			diceSides[i] = 0;
		}
		System.out.println("\nPossible Total Points: " + possibleScore);
		System.out.println("Possible Picks: " + possibleOpts);
		System.out.println("Total score: " + totalScore);
	}
	
	public void diceRoll() {
		Random rnd = new Random();
		int n = 0;
		for(int x=0; x < numDice; x++) {
			n = rnd.nextInt(6) + 1;
			dice[x] = n;
			diceSides[n-1]++;
		}
	}
	
	public boolean farkled() {
		//if no options we farkled, add one to counter and reset (if 3 farkles in row score back to 0)
		if(possibleOpts == " ") {
			if(farkleCounter+1 == 3){
				System.out.println("Three Farkles in a row! Reset total Score ");
				farkleCounter = 0;
			}else { farkleCounter++; }
			System.out.print("| ");
			for(int i=0; i < numDice; i++) {
				System.out.print(dice[i] + " | ");
			}
			Arrays.fill(diceSides, 0);
			numDice = 6;
			System.out.println("\n Shoot You Farkled! \n");
			farkleCounter++;
			possibleOpts = " ";
			possibleScore = 0;
			totalScore = 0;
			return true;
		} else { return false; }
	}
	public void passTurn() {
		//if turn passed reset possibles, dice to 6, add set score to total
		possibleScore=0; possibleOpts="";
		numDice = 6;
		score += totalScore;
		totalScore = 0;
		playerCheck = false;
		System.out.println("\nneed 10'000 to win you have: "  + score + "\n");
	}
	public boolean straightCheck(){
		for(int x=0; x<6; x++){
			if(diceSides[x] != 1){
				return false;
			}
		}
		return true;
	}
	//add ability for player to add 3 pairs, 2 triplets, straight, 4 of any num then pair
	public void checkNums() {
		String nums = " ";
		String pairs = " ";
		if(!playerCheck){
			if(numDice == 6){
				if(straightCheck()){
					possibleOpts += "123456";
					possibleScore += 1500;
				}
			}
			for(int x=0; x < numDice; x++) {
				// we have to add one because dice start at 1
				nums = Integer.toString(x + 1);
				if(dice[x] == 1) {
					possibleScore += 100;
					possibleOpts+= " 1 ";
				}
				if(dice[x] == 5) {
					possibleScore += 50;
					possibleOpts += " 5 ";
				}
				//fix how to know if 3 pairs
				if(diceSides[x] == 2){
					pairCount = 0;
					for(int n=x; n < numDice; n++){
						if(diceSides[n] == 2){
							pairCount++;
							pairs+=Integer.toString(n+1)+Integer.toString(n+1);
						}
					}
					if(pairCount == 3){ 
						possibleScore += 1500;
						possibleOpts += pairs;
					}
					pairs=" ";
				}
				if(diceSides[x] == 3){
					//need to check 1 because 300 points else 2 * 100 200
					if(x+1 == 1) { possibleScore += 300; } else { possibleScore += (x+1) * 100; }
					possibleOpts += nums+nums+nums;
					//checks to see if theres two triplets
					/*
					for(int n=0; n < numDice; n++) {
						if(n == x) { continue; }
						else if (diceSides[n] == 3) { 
							possibleScore += 2500;
							possibleOpts += Integer.toString(n)+Integer.toString(n)+Integer.toString(n);
						}
					}*/
				}
				if(diceSides[x] == 4) {
					possibleScore += 1000;
					possibleOpts += nums+nums+nums+nums;
					//checks to see if theres a pair
					for(int n=0; n < numDice; n++) {
						if(n == x) { break; }
						else if (diceSides[n] == 2) { 
							possibleScore+= 1500;
							possibleOpts += Integer.toString(n)+Integer.toString(n);
						}
					}
				}
				if(diceSides[x] == 5) {
					possibleScore+= 2000;
					possibleOpts += nums+nums+nums+nums+nums;
				}
				if(diceSides[x] == 6) {
					possibleScore += 3000;
					possibleOpts += nums+nums+nums+nums+nums+nums;
				}
			}
		}if (playerCheck){
				playerNumbers = Arrays.asList(playerPicks.split((" ")));
				char Num = playerNumbers.get(0).charAt(0);
				for(int x = 0; x < playerNumbers.size(); x++){
					if(Integer.parseInt(playerNumbers.get(x)) == 1) {
						totalScore += 100;
					}
					if(Integer.parseInt(playerNumbers.get(x)) == 5) {
						totalScore += 50;
					}
					if(playerNumbers.get(x).length() == 3) {
						//need to check 1 because 300 points else 2 * 100 200
						if(playerNumbers.get(x) == "1") { totalScore += 300; } else { totalScore += Character.getNumericValue(Num) * 100;}
						//checks to see if theres two triplets
						for(int n=0; n < numDice; n++) {
							if(n == x) { break; }
							else if (diceSides[n] == 3) { 
								totalScore += 2500;
								possibleOpts += Integer.toString(n)+Integer.toString(n)+Integer.toString(n);
							}
						}
					}
					if(playerNumbers.get(x).length() == 4) {
						totalScore += 1000;
						possibleOpts += nums+nums+nums+nums;
						//checks to see if theres a pair
						for(int n=0; n < numDice; n++) {
							if(n == x) { break; }
							else if (diceSides[n] == 2) { 
								totalScore += 1500;
								possibleOpts += Integer.toString(n)+Integer.toString(n);
							}
						}
					}
					if(playerNumbers.get(x).length() == 5) {
						totalScore += 2000;
					}
					if(playerNumbers.get(x).length() == 6) {
						totalScore += 3000;
					}
				}
				setEnd();
			}
	}
	
	public void setEnd(){
		playerPicks = playerPicks.replaceAll("\\s","");
		if((numDice-playerPicks.length()) > 0){
			if(playerPicks.length() > 1) { numDice = numDice - playerPicks.length(); }
			else { numDice--; }
		} else if(numDice == 1){
			numDice = 6;
		}
		playerPicks = "";
		playerCheck = false;
		possibleOpts = " ";
		possibleScore = 0;
	}
	
	public static void main(String[] args) {
		FarkleGame game = new FarkleGame();
		/*
		String todo = " "; // to tell what number to take or tell to spin again
		scan = new Scanner(System.in);
		System.out.print("what would you you like to do? ");
		todo = scan.next();
		*/
		Scanner scan = new Scanner(System.in);
		while(numDice > 0){
			game.diceRoll();
			game.quickSort(0, numDice - 1);
			game.checkNums();
			if(game.farkled()) { continue; }
			game.print();
			System.out.print("Please select dice or 'pass' to finish set: ");
			playerPicks = scan.nextLine();
			if(playerPicks.equals("pass")){
				game.passTurn();
				continue;
			} else{
				System.out.println();
				playerCheck = true;
				game.checkNums();
			}
		}
			
	}

}
