package logic.LemmingRoles;

import exceptions.RoleParseException;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.util.MyStringUtils;
import tp1.view.Messages;

public abstract class AbstractRole implements LemmingRole {
	private String name, help, shortcut;
	
	public AbstractRole(String name, String help, String shortcut) {
		this.name = name;
		this.help = help;
		this.shortcut = shortcut;	
		}
	
	public AbstractRole(AbstractRole r) {
		this.name = r.name;
		this.help = r.help;
	}
	
	public String getName() {
		return name;
	}

	public String getHelp() {
		return help;
	}
	
	public String getShortcut() { 
		return shortcut; 
	}
	public boolean matchName(String word) {
		return word.equalsIgnoreCase(this.getName()) || word.equalsIgnoreCase(this.getShortcut()) ;
	}
	
	public LemmingRole parse(String word) {
		if(matchName(word)) {
			return createInstance();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public abstract LemmingRole createInstance();
	
	
	public boolean receiveInteraction(GameItem other, Lemming lemming) {
		return false;
	}

	public boolean interactWith(Lemming receiver, Lemming lemming) {
		return false;
	}
	
	public boolean interactWith(Wall wall, Lemming lemming) {
		return false;
	}
	public boolean interactWith(ExitDoor door, Lemming lemming) {
		return false;
	}
	
	public boolean interactWith(MetalWall metal, Lemming lemming) {
		return false;
	}
	
	public abstract LemmingRole clone();
}
