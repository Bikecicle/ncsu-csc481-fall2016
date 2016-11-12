package time;

public class Timeline {
	protected Timeline anchor;
	protected long origin; // Origin point on anchor timeline
	protected long ticSize;

	public Timeline(Timeline anchor, long origin, long ticSize) {
		this.anchor = anchor;
		this.origin = origin;
		this.ticSize = ticSize;
	}

	public Timeline() {
		this.anchor = null;
	}

	public long getTime() {
		return (anchor.getTime() - origin) / ticSize;
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
	}
}
