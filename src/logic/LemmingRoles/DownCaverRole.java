package logic.LemmingRoles;

import tp1.logic.Direction;
import tp1.logic.GameObjectContainer;
import tp1.logic.Position;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole {
	private static final String NAME = Messages.DOWN_CAVER_ROL_NAME;
	private static final String HELP = Messages.DOWN_CAVER_ROL_HELP;
	private static final String SHORTCUT = Messages.DOWN_CAVER_ROL_SYMBOL;
	private String shortcut;
	private boolean hasCaved;
	
	public DownCaverRole() {
		this(false);
	}
	
	public DownCaverRole(boolean hasCaved) {
		super(NAME, HELP, SHORTCUT);
		this.hasCaved = hasCaved;
	}
	
	public DownCaverRole(DownCaverRole r) {
		super(r);
		this.hasCaved = r.hasCaved;
	}
	
	@Override
	public void start(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(Lemming lemming) {
		if(hasCaved) {
			lemming.fall();
			lemming.setFuerza(0);
			this.hasCaved = false;
		
		} else {
			lemming.disableRole();
			lemming.update();
		}
		
	}

	@Override
	public String getIcon(Lemming lemming) {
		return Messages.LEMMING_DOWN_CAVER;
	}

	@Override
	public LemmingRole createInstance() {
		return this;
	}

	@Override
	public boolean receiveInteraction(GameItem other, Lemming lemming) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean interactWith(Wall wall, Lemming lemming) {
		boolean interaccion = wall.getPos().equals(lemming.getPos().move(Direction.DOWN));
		if(interaccion) {
			this.hasCaved = true;
			wall.eliminar();
		}
		
		return interaccion;
	}
	
	@Override
	public String getName() {
		return("DownCaver");
	}

	@Override
	public LemmingRole clone() {
		return new DownCaverRole(this);
	}
	
	
}
