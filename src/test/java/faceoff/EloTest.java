package faceoff;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import faceoff.elo.ELO;

public class EloTest {

	@Test
	public void test_champion_wins() {
		ELO elo = new ELO(2400, 2000);
		elo.championWins();
		assertEquals(2403, elo.getChampionScore(), 0.5);
		assertEquals(1997, elo.getChallengerScore(), 0.5);
	}
	
	@Test
	public void test_challenger_wins() {
		ELO elo = new ELO(2400, 2000);
		elo.challengerWins();
		assertEquals(2371, elo.getChampionScore(), 0.5);
		assertEquals(2029, elo.getChallengerScore(), 0.5);
	}
	@Test
	public void test_both_win() {
		ELO elo = new ELO(2400, 2000);
		elo.bothWin();
		assertEquals(2403, elo.getChampionScore(), 0.5);
		assertEquals(2029, elo.getChallengerScore(), 0.5);
	}
	
	@Test
	public void test_both_lose() {
		ELO elo = new ELO(2400, 2000);
		elo.bothLose();
		assertEquals(2371, elo.getChampionScore(), 0.5);
		assertEquals(1997, elo.getChallengerScore(), 0.5);
	}
}
