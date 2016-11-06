package event;

import util.EConstant;

public class ClientDisconnectEvent extends Event{
	
	private int id;

	public ClientDisconnectEvent( int id ) {
		super(EConstant.CLIENT_DISCONNECT_EVENT, EConstant.PRIORITY_NETWORKING);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
