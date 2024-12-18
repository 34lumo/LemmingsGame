package logic.LemmingRoles;

import exceptions.RoleParseException;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public interface LemmingRole{
	public void start(Lemming lemming);  
	public void play(Lemming lemming); 
	public String getIcon(Lemming lemming); 
	

	public LemmingRole parse(String word) throws RoleParseException;
	
	public String getName();

	public String getHelp();
	
	public LemmingRole clone();
	
	
	
	
	public boolean receiveInteraction(GameItem other, Lemming lemming);

	public boolean interactWith(Lemming receiver, Lemming lemming);
	public boolean interactWith(Wall wall, Lemming lemming);
	public boolean interactWith(ExitDoor door, Lemming lemming);
	public boolean interactWith(MetalWall metal, Lemming lemming);
}

