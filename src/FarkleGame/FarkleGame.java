package FarkleGame;
import java.util.*;

public class FarkleGame {
	static int[] dice;
	static int[] diceSides; //how many of each side is rolled 
	static int numDice = 6;
	int score = 0;		//total points acquired in total by player 
	static int totalScore = 0; //amount of points in  current "set" 
	int possibleScore = 0; //total points possible in current "roll"
	String possibleOpts = " "; //possible number combinations
	static String playerPicks = " ";
	List<String> playerNumbers;
	static boolean playerCheck = false;

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
		//if(possibleOpts == " ") { possibleOpts = "FARKLE!"; }
		System.out.println("\nPossible Total Points: " + possibleScore);
		System.out.println("Possible Picks: " + possibleOpts);
		this.possibleOpts = " ";
		this.possibleScore = 0;
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
	
	public void checkNums() {
		String nums = " ";
		if(!playerCheck){
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
				if(diceSides[x] == 3) {
					//need to check 1 because 300 points else 2 * 100 200
					if(x+1 == 1) { possibleScore += 300; } else { possibleScore += (x+1) * 100;}
					possibleOpts += nums+nums+nums;
					//checks to see if theres two triplets
					for(int n=0; n < numDice; n++) {
						if(n == x) { break; }
						else if (diceSides[n] == 3) { 
							possibleScore += 2500;
							possibleOpts += Integer.toString(n)+Integer.toString(n)+Integer.toString(n);
						}
					}
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
		} else {
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
				playerCheck = false;
			}
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
		String whatDo;
		while(numDice > 0){
			System.out.println("what would you like to do? 'roll, 'end");
			whatDo = scan.next();
			if(whatDo=="roll"){ game.diceRoll(); } else if(whatDo=="end"){ break; }
			game.quickSort(0, numDice - 1);
			game.checkNums();
			game.print();
			System.out.print("Please select dice: ");
			playerPicks = scan.next();
			playerCheck = true;
			game.checkNums();
			System.out.println("current set score: " + totalScore + "\n");
			numDice--;
			
		}
			
	}

}
