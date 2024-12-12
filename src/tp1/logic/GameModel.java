package tp1.logic;

import exceptions.GameLoadException;
import exceptions.OffBoardException;
import logic.LemmingRoles.LemmingRole;

public interface GameModel {
	
	public boolean isFinished();
	public void exit();
	public void update();
	public boolean reinicio(int nLevel);
	public boolean initGame(int nLevel); 
	public boolean setRole(Position p, LemmingRole role) throws OffBoardException;;
	public void load(String filename) throws GameLoadException;
	
}
