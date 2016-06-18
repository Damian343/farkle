package FarkleGame;

public class Player {
	public String name;
	public int totalScore = 0;
	public String picks = "";
	public int farkleCount = 0;

	public Player(String name) {
		this.name = name;
	}

	public void setScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getScore() {
		return totalScore;
	}
	public String getName(){
		return name;
	}
	public void setPicks(String picks){
		this.picks = picks;
	}
	public String getPicks(){
		return picks;
	}
	public void resetFarkle() {
		farkleCount = 0;
	}
	public int getFarkleCount(){
		return farkleCount;
	}
	public void incrementFarkle(){
		farkleCount++;
	}
}