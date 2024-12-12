package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import exceptions.ObjectParseException;
import exceptions.OffBoardException;
import exceptions.RoleParseException;
import logic.LemmingRoles.DownCaverRole;
import logic.LemmingRoles.LemmingRole;
import logic.LemmingRoles.ParachuterRole;
import logic.LemmingRoles.WalkerRole;
import tp1.logic.FileGameConfiguration;
import tp1.logic.GameWorld;
import tp1.view.Messages;

public class GameObjectFactory{
	
	
	private static final List<GameObject> availableObjects = Arrays.asList(
			new Lemming(),
			new Wall(),
			new MetalWall(),
			new ExitDoor()
	);
	
	//igual que el commandGenerator
	
	public static GameObject parse(String word, GameWorld game) throws ObjectParseException, OffBoardException{		
		for (GameObject obj: availableObjects) {
			GameObject objetoDevuelto = obj.parse(word, game);
			if(objetoDevuelto != null) {
				return objetoDevuelto;
			}
		}
		throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT.formatted(word));
	}
}
