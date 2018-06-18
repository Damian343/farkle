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
		this.farkleCount++;
	}
	public int getSetScore(){
		return setScore;
	}
	public void increaseScore(int increment){
		this.setScore += increment;
	}
	public void resetSetScore() {
		this.setScore = 0;
	}
}