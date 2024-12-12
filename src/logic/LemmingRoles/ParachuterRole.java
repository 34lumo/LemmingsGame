package logic.LemmingRoles;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole{
	private static final String NAME = Messages.PARACHUTER_ROL_NAME;
	private static final String HELP = Messages.PARACHUTER_ROL_HELP;
	private static final String SHORTCUT = Messages.PARACHUTER_ROL_SYMBOL;
	
	
	public ParachuterRole() {
		super(NAME, HELP, SHORTCUT);
	}
	
	public ParachuterRole(ParachuterRole r) {
		super(r);
	}
	
	@Override
	public void play(Lemming lemming) {
		lemming.setFuerza(0);
		if(lemming.isInAir()) {
			lemming.isFalling();
			
			
		} else {
			lemming.disableRole();
			lemming.update();
		}
		
	}

	@Override
	public String getIcon(Lemming lemming) {
		return Messages.LEMMING_PARACHUTE;
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
		return("Parachuter");
	}

	@Override
	public LemmingRole clone() {
		return new ParachuterRole(this);
	}
}
