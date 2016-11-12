package time;

public class Timeline {
	protected Timeline anchor;
	protected long origin; // Origin point on anchor timeline
	protected long ticSize;
	protected long time;

	public Timeline(Timeline anchor, long origin, long ticSize) {
		this.anchor = anchor;
		this.origin = origin;
		this.ticSize = ticSize;
		this.time = 0;
	}

	public Timeline() {
		this.anchor = null;
	}

	public long getTime() {
		time = (anchor.getTime() - origin) / ticSize;
		return time;
	}

	public long getOrigin() {
		return origin;
	}
	
	public void setOrigin(long origin) {
		this.origin = origin;
	}

	public long getTicSize() {
		return ticSize;
	}
	
	public void reset() {
		origin = anchor.getTime();
		time = 0;
	}
}
