package tp1.logic;

import logic.LemmingRoles.LemmingRole;
import tp1.logic.gameobjects.GameItem;

public interface GameWorld {
	
	public boolean isInPosition(Position p);
	public boolean isSolid(Position p);
	public boolean isExit(Position p);
	public boolean isAlive();
	
	public void lemmingExit();
	public void lemmingDead();
	
	public boolean receiveInteractionsFrom(GameItem objeto);
}
