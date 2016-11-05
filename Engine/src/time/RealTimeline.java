package time;

public class RealTimeline extends Timeline {
	
	public RealTimeline() {
		super();
	}
	
	@Override
	public long getTime() {
		return System.nanoTime();
	}
}
