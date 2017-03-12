package faceoff.elo;

import faceoff.competition.Competitor;

public class ELO {

	private Competitor champion;
	private double championR;
	private double championE;
	private Competitor challenger;
	private double challengerR;
	private double challengerE;

	public static final double K = 32.0;
	public static final double DEFAULT_SCORE = 2000.0;

	public ELO(Competitor champion, Competitor challenger) {
		this.champion = champion;
		this.challenger = challenger;

		this.championR = Math.pow(10, (this.champion.getScore() / 400.0));
		this.challengerR = Math.pow(10, (this.challenger.getScore() / 400.0));

		double divisor = this.championR + this.challengerR;
		this.championE = this.championR / divisor;
		this.challengerE = this.challengerR / divisor;
	}

	public double getChampionR() {
		return championR;
	}

	public double getChampionE() {
		return championE;
	}

	public double getChallengerR() {
		return challengerR;
	}

	public double getChallengerE() {
		return challengerE;
	}

	public void championWins() {
		champion.setScore(champion.getScore() + (ELO.K * (1 - championE)));
		challenger.setScore(challenger.getScore() + (ELO.K * (-challengerE)));
	}

	public void challengerWins() {
		champion.setScore(champion.getScore() + (ELO.K * (-championE)));
		challenger.setScore(challenger.getScore() + (ELO.K * (1 - challengerE)));
	}

	public void bothWin() {
		champion.setScore(champion.getScore() + (ELO.K * (1 - championE)));
		challenger.setScore(challenger.getScore() + (ELO.K * (1 - challengerE)));
	}

	public void bothLose() {
		champion.setScore(champion.getScore() + (ELO.K * (-championE)));
		challenger.setScore(challenger.getScore() + (ELO.K * (-challengerE)));
	}
}
