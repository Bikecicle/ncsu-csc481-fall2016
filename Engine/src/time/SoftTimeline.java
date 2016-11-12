package time;

public class SoftTimeline extends Timeline {

	private double scale; // For speeding up or slowing down time
	private long keyframe; // Timestamp (of anchor) at last change in scale
	private long keyframeTime; // Timestamp (of this timeline) at last change in
								// scale

	public SoftTimeline(Timeline anchor, long origin, long ticSize, double scale) {
		super(anchor, origin, ticSize);
		this.scale = scale;
		this.keyframe = origin;
		this.keyframeTime = 0;
	}

	public void setScale(double scale) {
		System.out.println(getTime());
		keyframeTime = this.getTime();
		keyframe = anchor.getTime();
		this.scale = scale;
		System.out.println(getTime());
	}

	public void multiplyScale(double a) {
		setScale(scale * a);
	}

	@Override
	public long getTime() {
		return (long) (((anchor.getTime() - keyframe) * scale) + keyframeTime);
	}

	public double getScale() {
		return scale;
	}

	public long getKeyframe() {
		return keyframe;
	}

	public long getKeyframeTime() {
		return keyframeTime;
	}
}
