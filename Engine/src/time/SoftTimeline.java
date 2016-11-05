package time;

public class SoftTimeline extends Timeline {

	private double scale; // For speeding up or slowing down time
	private long offset; // For adjusting after change in scale
	private long keyframe; // Timestamp (of anchor) at last change in scale

	public SoftTimeline(Timeline anchor, long origin, long ticSize, double scale) {
		super(anchor, origin, ticSize);
		this.scale = scale;
		this.offset = 0;
		this.keyframe = origin;
	}

	public void setScale(double scale) {
		long anchorTime = anchor.getTime();
		offset += (anchorTime - keyframe) * (1 - scale);
		keyframe = anchorTime;
		this.scale = scale;
	}

	@Override
	public long getTime() {
		return (long) ((super.getTime() * scale) - offset);
	}

	public double getScale() {
		return scale;
	}

	public long getOffset() {
		return offset;
	}
	
	public long getKeyframe() {
		return keyframe;
	}
}
