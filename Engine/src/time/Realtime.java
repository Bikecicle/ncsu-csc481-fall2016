package time;

public class Realtime extends Timeline {
	
	public Realtime() {
		super();
	}
	
	@Override
	public long getTime() {
		return System.nanoTime();
	}
}
