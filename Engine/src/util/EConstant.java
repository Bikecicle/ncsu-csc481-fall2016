package util;

public class EConstant {

	// Networking
	public static final int PORT = 6666;

	// Objects
	public static final double PLAYER_WIDTH = 2;
	public static final double PLAYER_HEIGHT = 2;

	// Priorities
	public static final int PRIORITY_NETWORKING = 0;
	public static final int PRIORITY_INPUT = 1;
	public static final int PRIORITY_RENDERING = 2;
	public static final int PRIORITY_GAME_LOGIC = 3;
	public static final int PRIORITY_PHYSICS = 4;
	public static final int PRIORITY_MOVEMENT = 5;


	// Event id's
	public static final int RENDER_COMPONENT_EVENT = 1;
	public static final int SERVICE_COMPONENT_EVENT = 2;
	public static final int KEY_PRESSED_EVENT = 3;
	public static final int KEY_RELEASED_EVENT = 4;
	public static final int OBJECT_MOVED_EVENT = 5;
	public static final int COLLISION_EVENT = 6;
	public static final int MOMENTUM_TRANSFER_EVENT = 7;
	public static final int OBJECT_DAMAGE_EVENT = 8;
	public static final int RESPAWN_EVENT = 9;
	public static final int CLIENT_CONNECT_EVENT = 10;
	public static final int CLIENT_DISCONNECT_EVENT = 11;
	public static final int RENDER_ALL_EVENT = 12;
	public static final int SERVICE_ALL_EVENT = 13;

	// Keys
	public static final int KEY_UP = 38;
	public static final int KEY_DOWN = 40;
	public static final int KEY_LEFT = 37;
	public static final int KEY_RIGHT = 39;

	// Physics
	public static final double PLAYER_JUMP_VELOCITY = 50;
	public static final double PLAYER_ACCELERATION = 400;
	public static final double PLAYER_MAX_VELOCITY = 50;
	public static final double GRAVITY = -100;
	public static final double COLLISION_ELASTICITY_COEFFICIENT = 0.5;
	public static final double FRICTION_COEFFICIENT = 0.9;
	public static final double MINIMUM_VELOCITY = 0.01;

	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;
	public static final double WORLD_WIDTH = 100;
	public static final double WORLD_HEIGHT = 100;
	
	// Game Loop Parameters
	public static final double NANOSECONDS_IN_SECOND = 1000000000.0;
	public static final long GAME_LOOP_DELTA = (long) (NANOSECONDS_IN_SECOND / 120);
	public static final int FRAME_RATE = 2; // In game loop tics
	public static final int PHYSICS_UPDATE_RATE = 1; // In game loop tics
	
	
}