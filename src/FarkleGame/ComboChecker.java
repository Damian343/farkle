package FarkleGame;

/**
 * Created by damian on 6/17/2018.
 */
public class ComboChecker {

    /*
    need to add function that allows
    just getting the players points from
    possible options available
     */

    private Dice dice;
    private StringBuilder possibleOpts;

    ComboChecker(Dice dice) {
        this.dice = dice;
        this.possibleOpts = new StringBuilder();
    }

    public StringBuilder getPossibleOpts() {
        return possibleOpts;
    }

    public void setPossibleOpts(StringBuilder possibleOpts) {
        this.possibleOpts = possibleOpts;
    }

    public Dice getDice() {
        return dice;
    }

    public void resetPossibleOpts() {
        this.possibleOpts.setLength(0);
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    private void straightCheck(int[] sides) {
        for(int x = 0; x < 6; x++){
            if(sides[x] == 1){
                possibleOpts.append(x);
            } else {
                possibleOpts.setLength(0);
            }
        }
    }

    private void threePairCheck(int[] sides) {
        int pairCount=0;
        String pairs="";

        for(int x=0; x<6; x++) {
            if(sides[x] == 2){
                pairCount++;
                String n = Integer.toString(x+1);
                pairs += n + n;
            }
        }
        if(pairCount == 3) {
            possibleOpts.append(pairs).append(" ");
        }
    }

    public boolean twoTripletCheck(int[] sides) {
        int tripCount = 0;
        String trips = "";

        for(int x=0; x < dice.getNumDice(); x++) {
            if (sides[x] == 3) {
                String n = Integer.toString(x);
                tripCount++;
                trips += n + n + n;
            }
        }
        if(tripCount==2) {
            possibleOpts.append(trips).append(" ");
            return true;
        }
        return false;
    }

    public void checkNumbers(){
        int[] sides = dice.getDiceSides();
        int[] dieNums = dice.getDice();

        if(dice.getNumDice() == 6){
            twoTripletCheck(sides);
            threePairCheck(sides);
            straightCheck(sides);
        }

        String option = "";

        for (int x = 0; x < dice.getNumDice(); x++){
            String n = Integer.toString(x);
            option = "";
            if(dieNums[x] == 1) {
                possibleOpts.append("1 ");
            }
            if(dieNums[x] == 5) {
                possibleOpts.append("5 ");
            }
            if(sides[x] == 3){
                option = n+n+n+" ";
                possibleOpts.append(option);
            }
            //checks to see if pair and a quad
            if(sides[x] == 4) {
                option = n+n+n+n;
                possibleOpts.append(option);
                String m = "";
                for(int z = 0; z < dice.getNumDice(); z++) {
                    if(sides[z] == 2){
                        m = Integer.toString(z);
                        possibleOpts.append(m).append(m);
                    }
                }
                possibleOpts.append(" ");
            }
            if(sides[x] == 5) {
                option = n+n+n+n+n+" ";
                possibleOpts.append(option);
            }
            if(sides[x] == 6) {
                option = n+n+n+n+n+n+" ";
                possibleOpts.append(option);
            }
        }
    }

}
