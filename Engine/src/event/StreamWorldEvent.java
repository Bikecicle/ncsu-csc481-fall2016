package event;

import java.util.List;

import component.Component;
import util.EConstant;

public class StreamWorldEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5900366372536261883L;
	private List<Component> componentStream;

	public StreamWorldEvent(long timestamp, List<Component> componentStream) {
		super(timestamp, EConstant.STREAM_WORLD_EVENT, EConstant.PRIORITY_NETWORKING);
		this.componentStream = componentStream;
	}

	public List<Component> getComponentStream() {
		return componentStream;
	}
}
