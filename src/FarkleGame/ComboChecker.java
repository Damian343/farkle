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

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    private void straightCheck() {
        int[] sides = dice.getDiceSides();
        for(int x = 0; x < 6; x++){
            if(sides[x] == 1){
                possibleOpts.append(x);
            } else {
                possibleOpts.setLength(0);
            }
        }
    }

    private void threePairCheck() {
        int pairCount=0;
        String pairs="";

        for(int x=0; x<6; x++) {
            if(dice.diceSides[x] == 2){
                pairCount++;
                pairs += Integer.toString(x+1)+Integer.toString(x+1);
            }
        }
        System.out.println(pairs);
        if(pairCount == 3){ possibleOpts.append(pairs).append(" "); player.increaseScore(1500); }
    }

    private void twoTripletCheck() {
        int tripCount = 0;
        for(int n=0; n < dice.getNumDice(); n++) {
            //if 3 of one number add to possible opts
            String num = Integer.toString(n);

            if (dice.getDiceSides([n] == 3) {
                tripCount++;
                possibleOpts.append(num).append(num).append(num);
            }
        }
        if(tripCount==2) {
            possibleOpts.append(" ");
        } else if(possibleOpts.length() < 6){
            possibleOpts
        }
    }

    private void checkNumbers(){
        String nums = "";

        if(dice.getNumDice() == 6){
            twoTripletCheck();
            threePairCheck();

        }
    }

}
