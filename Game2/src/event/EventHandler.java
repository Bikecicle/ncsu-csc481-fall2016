package event;

public interface EventHandler {
	
	public void register();
	
	public void onEvent(Event event);
	
	public int getGuid();

}
