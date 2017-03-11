package faceoff.elo;

public class ELO {

	private double championScore;
	private double championR;
	private double championE;
	private double challengerScore;
	private double challengerR;
	private double challengerE;

	public static final double K = 32.0;

	public ELO(double championScore, double challengerScore) {
		this.championScore = championScore;
		this.challengerScore = challengerScore;
		
		this.championR = Math.pow(10, (this.championScore / 400.0));
		this.challengerR = Math.pow(10, (this.challengerScore / 400.0));
		
		double divisor = this.championR + this.challengerR;
		this.championE = this.championR / divisor;
		this.challengerE = this.challengerR / divisor;
	}

	public double getChampionScore() {
		return championScore;
	}
	
	public double getChampionR(){
		return championR;
	}
	
	public double getChampionE() {
		return championE;
	}

	public double getChallengerScore() {
		return challengerScore;
	}
	
	public double getChallengerR(){
		return challengerR;
	}

	public double getChallengerE() {
		return challengerE;
	}

	public void championWins() {
		championScore = championScore + (ELO.K * (1 - championE));
		challengerScore = challengerScore + (ELO.K * (-challengerE));
	}

	public void challengerWins() {
		championScore = championScore + (ELO.K * (-championE));
		challengerScore = challengerScore + (ELO.K * (1 - challengerE));
	}

	public void bothWin() {
		championScore = championScore + (ELO.K * (1 - championE));
		challengerScore = challengerScore + (ELO.K * (1 - challengerE));
	}

	public void bothLose() {
		championScore = championScore + (ELO.K * (-championE));
		challengerScore = challengerScore + (ELO.K * (-challengerE));
	}
}
