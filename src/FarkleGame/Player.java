package FarkleGame;

public class Player {
	public String name;
	public int totalScore = 0;
	public int currSetScore = 0;
	public int rollScore = 0;
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
	public void setSetScore(int currSetScore){
		this.currSetScore = currSetScore;
	}
	public int getSetScore(){
		return currSetScore;
	}
	public void setRollScore(int rollScore){
		this.rollScore = rollScore;
	}
	public int getRollScore(){
		return rollScore;
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
	public int getFarkleCount(){
		return farkleCount;
	}
	public int incrementFarkle(){
		farkleCount++;
		return farkleCount;
	}
}