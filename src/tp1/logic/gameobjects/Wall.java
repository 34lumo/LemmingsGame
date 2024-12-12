package tp1.logic.gameobjects;

import exceptions.ObjectParseException;
import exceptions.OffBoardException;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.util.MyStringUtils;
import tp1.view.ConsoleView;
import tp1.view.Messages;

public class Wall extends GameObject {
	
	
	public Wall(Position pos, GameWorld game) {
		super(pos, game);
		
	}
	
	Wall() {}
	
	public Wall(Wall pared) {
		super(pared);
	}
	
	@Override
    public GameObject copy() {
    	return new Wall(this);
    }
	
	public String getIcon() {
		return Messages.WALL;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void update() {
		
	}
	
	@Override
	public boolean isSolid(Position pos) {
		return true;
	}
	
	@Override
	public boolean receiveInteraction(GameItem other) {
		return other.interactWith(this);
  }
	
	@Override
	public String getName() {
		return "Wall";
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
					return new Wall(pos, game);
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