package time;

public class Timeline {
	private Timeline anchor;
	private long origin;
	private long ticSize;
	private long time;
	private double scale;

	public Timeline() {
		this.anchor = null;
	}

	public Timeline(Timeline anchor, long origin, long ticSize) {
		this.anchor = anchor;
		this.origin = origin;
		this.ticSize = ticSize;
		this.time = 0;
		this.scale = 1.0;
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

	public void setTicSize(long ticSize) {
		this.ticSize = ticSize;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
}
