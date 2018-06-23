package FarkleGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by damian on 6/19/2018.
 */
public class Game {
    private Dice dice;
    private ComboChecker checker;
    private GameBoard gameBoard;
    private ArrayList<Player> players;
    private int playerIdx;
    private Player player;
    private Random random;

    Game(String[] names) {
        players = new ArrayList<Player>();
        for(String name : names){
            players.add(new Player(name));
        }
        dice = new Dice();
        checker = new ComboChecker(dice);
        gameBoard = new GameBoard();
        random = new Random();
    }

    public void newGame() {
        playerIdx = random.nextInt(players.size());
        player = players.get(playerIdx);
        gameBoard.startState();
    }

    public void roll() {
        dice.rollDice();
        checker.setDice(dice);
        gameBoard.setDice(dice);
        checker.checkNumbers();
    }

    public void playerChanger() {
        playerIdx++;
        if(playerIdx == players.size()){
            playerIdx = 0;
        }
        player = players.get(playerIdx);
    }

    private void farkled() {
        player.incrementFarkle();
        if(player.getFarkleCount() == 3) {
            player.resetSetScore();
            player.resetFarkle();
        }
        gameBoard.farkle();
        playerChanger();
        dice.resetDice();
    }

    private void passTurn() {
        if(gameBoard.turnAlert()){
            playerChanger();
            gameBoard.playerChangePass(Player player);
            checker.resetPossibleOpts();
        } else {
            int tempScore = player.getSetScore();
            player.resetSetScore();
            playerChanger();
            player.changeSetScore(tempScore);

            gameBoard.playerChangeTakePoints(Player player);
            dice.resetDice();
        }
    }

    private void endGame() {
        gameBoard.endGame();
        dice.resetDice();
    }
}
