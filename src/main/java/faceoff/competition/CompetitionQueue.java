package faceoff.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CompetitionQueue {

	private LinkedList<Competitor> queue;
	
	public CompetitionQueue() {
		queue = new LinkedList<Competitor>();
	}
	
	public List<Competitor> getQueue(){
		return queue;
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public int size(){
		return queue.size();
	}
	
	public Competitor pop(){
		return queue.removeFirst();
	}
	
	public void push(Competitor competitor){
		queue.add(competitor);
	}
	
	public void shuffle(){
		Collections.shuffle(queue);
	}
	
	public void insert_shuffle(Competitor competitor){
		if (queue.isEmpty()){
			queue.addFirst(competitor);
		} else {	
			int random_position = (int)(Math.random() * queue.size());
			queue.add(random_position, competitor);
		}
	}
	
	public List<Competitor> getBest(int howMany){
		List<Competitor> best = new ArrayList<Competitor>(queue.size());
		best.addAll(queue);
		Collections.sort(best, new Comparator<Competitor>() {
			@Override
			public int compare(Competitor o1, Competitor o2) {
				return o1.getScore() > o2.getScore() ? 1 : -1;
			}
		});
		return best.subList(0, howMany - 1);
	}

	public List<Competitor> getWorst(int howMany){
		List<Competitor> best = new ArrayList<Competitor>(queue.size());
		best.addAll(queue);
		Collections.sort(best, new Comparator<Competitor>() {
			@Override
			public int compare(Competitor o1, Competitor o2) {
				return o1.getScore() > o2.getScore() ? -1 : 1;
			}
		});
		return best.subList(0, howMany - 1);
	}
}
