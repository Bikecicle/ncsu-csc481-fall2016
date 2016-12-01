package event;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EventLog {

	private PriorityQueue<Event> log;

	public EventLog() {
		log = new PriorityQueue<Event>(new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				return (int) Math.signum(o2.getTimestamp() - o1.getTimestamp());
			}
		});
	}

	public void add(Event event) {
		log.add(event);
	}
	
	public Event poll(){
		return log.poll();
	}
}
