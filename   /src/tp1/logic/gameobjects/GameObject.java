package tp1.logic.gameobjects;

import exceptions.ObjectParseException;
import exceptions.OffBoardException;
import logic.LemmingRoles.LemmingRole;
import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.logic.GameStatus;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject implements GameItem{

	protected Position pos;
	protected boolean isAlive;
	protected GameWorld game;
	
	public GameObject(Position pos, GameWorld game) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	GameObject() {
	}
	
	public GameObject(GameObject obj) {
		this.isAlive = obj.isAlive;
		this.pos = obj.pos;
		this.game = obj.game;
	}
	
	public abstract String getName();
	
	protected boolean matchObjectName(String name) {
		return getName().equalsIgnoreCase(name);
	}
	
	public abstract GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException;
	
	public abstract GameObject copy();
	
	public boolean isInPosition(Position p) {
		return this.pos.equals(p);
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void eliminar() {
		this.isAlive = false;

	}
	public boolean setRole(LemmingRole role) {
		return false;
	}
	
	
	public boolean isSolid(Position pos){
		return false;
	}
	
	public boolean isExit(Position pos) {
		return false;
	}
	
	public abstract void update();
		
	public abstract String getIcon();
	
	public boolean receiveInteraction(GameItem other) {
		return false;
	}

	public boolean interactWith(Lemming lemming) {
		return false;
	}
	
	public boolean interactWith(Wall wall) {
		return false;
	}
	
	public boolean interactWith(ExitDoor door) {
		return false;
	}
	
	public boolean interactWith(MetalWall metal) {
		return false;
	}
	
}