function update() {
	var RespawnEvent = Java.type("event.RespawnEvent");
	var event = new RespawnEvent(time, other_guid, 0, 0);
	event_manager.raise(event);
}