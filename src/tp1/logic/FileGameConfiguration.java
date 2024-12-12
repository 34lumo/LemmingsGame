package tp1.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import exceptions.FileConfigException;
import exceptions.GameLoadException;
import exceptions.GameParseException;
import exceptions.ObjectParseException;
import exceptions.OffBoardException;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.Messages;

public class FileGameConfiguration implements GameConfiguration {
	public static final GameConfiguration NONE = new FileGameConfiguration();
	private List<GameObject> objects;
	private int ciclo, lemmingsMuertos, lemmingsActivos, lemmingsGanan, lemmingsParaGanar;
	
	private FileGameConfiguration() {}
	//METODOS GET Y SET
	public int getCycle() {
		return this.ciclo;
	}
	
	public int numLemmingsToWin() {
		// TODO Auto-generated method stub
		return lemmingsParaGanar;
	}
	
	public int numLemmingsInBoard() {
		return this.lemmingsActivos;	
	}	

	public int numLemmingsDead() {
		return this.lemmingsMuertos;
	}
		
	public int numLemmingsExit() {
		return this.lemmingsGanan;
	}
	
	public FileGameConfiguration(String fileName, GameWorld game) throws FileConfigException {
		try (BufferedReader entrada = new BufferedReader(new FileReader(fileName))) {
		    this.objects = new ArrayList<>(); //ME CREO UN NUEVO OBJETO QUE ES LA LISTA DE LOS OBJETOS DEL JUEGO
		    
		     String linea = entrada.readLine(); //LEO LA PRIMERA LINEA DEL FICHERO QUE CONTIENE LOS MENSAJES CICLO, LEMMINGS MUERTOS, ETC.
		     parseDeEstado(linea); // LA PARSEO PARA PASAR DE STRING A ENTEROS DENTRO DEDL PARSE.

		     linea = entrada.readLine(); // LEO LA SIGUIENTE LINEA DEL FICHERO EN DONDE YA SE ENCUENTRAN LOS OBJETOS DEL JUEGO
		     while (linea != null && !linea.isEmpty()) { //MIENTRAS QUE LA LINEA LEIDA NO SEA NULA Y NO HAYA TERMINADO LA LECTURA DE OBJETOS ENTONCES...
		         GameObject obj = GameObjectFactory.parse(linea, game); //PARSEO CADA OBJETO
		         objects.add(obj); //LO AÑADO EN MI NUEVA LISTA DE OBJETOS CLONES
		         linea = entrada.readLine();//LEO EL SIGUINTE OBJETO DEL FICHERO
		     }
		 } catch (FileNotFoundException a) {
		    	throw new FileConfigException(Messages.FILE_NOT_FOUND.formatted(fileName));
	     } catch (IOException e) {
	            throw new FileConfigException(Messages.READ_ERROR.formatted(fileName));
		 } catch (ObjectParseException | OffBoardException o) {
		        throw new FileConfigException(o.getMessage());
		 }
    }
	
	public GameObjectContainer reciveContenedor() {
		GameObjectContainer container = new GameObjectContainer();
		for(GameObject obj: objects) {
			container.add(obj.copy());
		}
		return container;
	}
	
	public void parseDeEstado(String linea) throws FileConfigException{
			String[] parts = linea.trim().split("\\s+");
			try {
			   if(parts.length == 5) {
			       ciclo = Integer.parseInt(parts[0]);
			       lemmingsActivos = Integer.parseInt(parts[1]);
				   lemmingsMuertos = Integer.parseInt(parts[2]);
				   lemmingsGanan = Integer.parseInt(parts[3]);
				   lemmingsParaGanar = Integer.parseInt(parts[4]);	
			   }
			   else {
				   throw new FileConfigException(Messages.INVALID_GAME_STATUS.formatted(linea));//NECESITAMOS SABES QUE MENSAJE PONERLE
			   }
			} catch (NumberFormatException e) {
				throw new FileConfigException(Messages.INVALID_GAME_STATUS.formatted(linea));
			}
	}
}