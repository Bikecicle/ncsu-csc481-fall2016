function update() {
	var DestroyObjectEvent = Java.type("event.DestroyObjectEvent");
	var event = new DestroyObjectEvent(time, this_guid);
	event_manager.raise(event);
}