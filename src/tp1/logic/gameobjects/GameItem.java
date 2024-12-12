package tp1.logic.gameobjects;

import exceptions.ObjectParseException;
import exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public interface GameItem {
	
		public boolean receiveInteraction(GameItem other);

		public boolean interactWith(Lemming lemming);
		public boolean interactWith(Wall wall);
		public boolean interactWith(ExitDoor door);
		public boolean interactWith(MetalWall metal);

		public boolean isSolid(Position pos);
		public boolean isAlive();
		public boolean isExit(Position pos);
		public boolean isInPosition(Position pos);
		//public abstract GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException;
}
