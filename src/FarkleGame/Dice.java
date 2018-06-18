package FarkleGame;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by damian on 6/17/2018.
 */
public class Dice {

    private int[] diceSides;
    private int[] dice;
    private int   numDice;
    private Random random;

    Dice(){
        diceSides = new int[6];
        dice = new int[6];
        numDice = 6;
        random = new Random();
    }

    public int getNumDice(){
        return numDice;
    }

    public int[] getDiceSides() {
        return diceSides;
    }

    public void setDiceSides(int[] diceSides) {
        this.diceSides = diceSides;
    }

    protected void rollDice() {
        resetDice();
        int n;
        for(int x=0; x < numDice; x++) {
            n = random.nextInt(6) + 1;
            dice[x] = n;
            diceSides[n-1]++;
        }
        System.out.println(Arrays.toString(dice));
        System.out.println(Arrays.toString(diceSides));
    }

    public void diceRemover(int amount) {
        if((numDice - amount) > 0){
            numDice -= amount;
        } else {
            numDice = 6;
            resetDice();
        }
    }

    private void resetDice() {
        Arrays.fill(diceSides, 0);
        Arrays.fill(dice, 0);
    }

}
