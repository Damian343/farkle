package FarkleGame;

class Player {
	private final String name;
	private int totalScore = 0;
	private int farkleCount = 0;

	public Player(String name) {
		this.name = name;
	}
	public void setScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public void incrementScore(int increment){
		totalScore += increment;
	}
	public int getScore() {
		return totalScore;
	}
	public String getName(){
		return name;
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