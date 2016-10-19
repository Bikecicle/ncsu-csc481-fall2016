package util;

public class EConstant {

	// Networking
	public static final int PORT = 6666;

	// Objects
	public static final double PLAYER_WIDTH = 2;
	public static final double PLAYER_HEIGHT = 2;

	// Priorities
	public static final int PRIORITY_HIGHEST = 1;
	public static final int PRIORITY_HIGH = 2;
	public static final int PRIORITY_MEDIUM = 3;
	public static final int PRIORITY_LOW = 4;
	public static final int PRIORITY_LOWEST = 5;

	// Event id's
	public static final int RENDER_EVENT = 1;
	public static final int UPDATE_PHYSICS_EVENT = 2;
	public static final int KEY_PRESSED_EVENT = 3;
	public static final int KEY_RELEASED_EVENT = 4;
	public static final int OBJECT_MOVED_EVENT = 5;
	public static final int COLLISION_EVENT = 6;
	public static final int MOMENTUM_TRANSFER_EVENT = 7;
	public static final int OBJECT_DAMAGE_EVENT = 8;
	public static final int RESPAWN_EVENT = 9;

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
	public static final double FRICTION_COEFFICIENT = 0.9999;

	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;
	public static final double WORLD_WIDTH = 100;
	public static final double WORLD_HEIGHT = 100;

	public static final long GAME_LOOP_DT = 5333333;

}
