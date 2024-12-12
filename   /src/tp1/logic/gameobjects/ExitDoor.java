package tp1.logic.gameobjects;
import exceptions.ObjectParseException;
import exceptions.OffBoardException;
import logic.LemmingRoles.LemmingRole;
import tp1.logic.*;
import tp1.view.Messages;
public class ExitDoor extends GameObject {
	
	//CONSTRUCTORA DE LA PUERTA
	public ExitDoor (Position pos, GameWorld game) {
		super(pos, game);
	}
	
	private boolean isExit;
	
	public ExitDoor(ExitDoor exit) {
    	super(exit);
	}
	
	ExitDoor() {	
	}

	@Override
	public boolean isExit(Position pos) {
		return this.pos.equals(pos);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub	
	}

	public String getIcon() {
		// TODO Auto-generated method stub
		return Messages.EXIT_DOOR;
	}
	
	@Override
    public GameObject copy() {
    	return new ExitDoor(this);
    }

	@Override
	public String getName() {
		return "ExitDoor";
	}
	
	@Override
	public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException {
		String[] words = line.trim().split("\\s+");
		String nombre = words[1];
		if(matchObjectName(words[1])) {
			try {
				String[] w = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
				int fila = Integer.parseInt(w[0]);
				int col = Integer.parseInt(w[1]);
				Position pos = new Position(col, fila);
				if(!pos.isInBoard()) {
					throw new OffBoardException(Messages.OBJECT_OFF_WORLD_POSITION.formatted(line));
				} 
				if(pos.isInBoard()) {
					return new ExitDoor(pos, game);
				}
			}catch (ArrayIndexOutOfBoundsException e1) {
				throw new ObjectParseException(Messages.INVALID_GAME_OBJECT.formatted(line));
			}
			catch (NumberFormatException e2) {
				throw new ObjectParseException(Messages.INVALID_POSITION.formatted(line));
			}
		}
		return null;
	}
	
	
}
	
	

