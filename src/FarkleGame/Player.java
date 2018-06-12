package FarkleGame;

class Player {
	private final String name;
	private int totalScore;
	private int farkleCount;
	private int setScore;

	public Player(String name) {
		this.name = name;
		this.totalScore  = 0;
		this.farkleCount = 0;
		this.setScore    = 0;
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
	public int getSetScore(){
		return setScore;
	}
	public void changeSetScore(int newScore){
		setScore += newScore;
	}
	public void resetSetScore() {
		setScore = 0;
	}
}