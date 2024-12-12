package logic.LemmingRoles;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class WalkerRole extends AbstractRole{
	private static final String NAME = Messages.WALKER_ROL_NAME;
	private static final String HELP = Messages.WALKER_ROL_HELP;
	private static final String SHORTCUT = Messages.WALKER_ROL_SYMBOL;
	private static final String ICON_RIGHT = Messages.LEMMING_RIGHT;
	private static final String ICON_LEFT = Messages.LEMMING_LEFT;
	
	public WalkerRole() {
		super(NAME, HELP, SHORTCUT);
	}
	
	public WalkerRole(WalkerRole r) {
		super(r);
	}
	
	public void play(Lemming l) {
		l.walkOrFall();
	}
	
	public String getIcon(Lemming lemming) {
		String strLemming = "";
		switch (lemming.getDir()) {
		case LEFT: strLemming = Messages.LEMMING_LEFT;
			break;
		case RIGHT: strLemming = Messages.LEMMING_RIGHT;
			break;
		default: 
		}
		return strLemming;
	}
	
	
	@Override
	public void start(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public LemmingRole createInstance() {
		return this;
	}
	
	@Override
	public String getName() {
		return("Walker");
	}
	
	@Override
	public LemmingRole clone() {
		return new WalkerRole(this);
	}
	

}