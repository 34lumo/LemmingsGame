package tp1.logic;
import exceptions.GameLoadException;
import exceptions.OffBoardException;
import logic.LemmingRoles.LemmingRole;
import logic.LemmingRoles.ParachuterRole;
import logic.LemmingRoles.WalkerRole;
//las funciones donde tienen que estar
import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class Game implements GameStatus, GameWorld, GameModel {
	private GameObjectContainer obj;
	public static final int DIM_X = 10;
	public static final int DIM_Y = 10; 
	private int ciclo;
	private int nLevel;
	private boolean exit = false;
	private int lemmingsMuertos, lemmingsActivos, lemmingsGanan, numLemmingsToWin;
	private GameConfiguration conf = FileGameConfiguration.NONE;
	private String fileName;
	
	//CONSTRUCTORA DEL GAME
	public Game(int nLevel) {
		this.nLevel = nLevel;
		initGame(nLevel);
	}
	
	
	//METODO QUE HACE LA CARGA DEL FICHERO tocar aqui
		public void load(String fileName) throws GameLoadException {
			conf = new FileGameConfiguration(fileName, this);
			this.fileName = fileName;
			this.ciclo = conf.getCycle();
			this.lemmingsActivos = conf.numLemmingsInBoard();
			this.lemmingsMuertos = conf.numLemmingsDead();
			this.lemmingsGanan = conf.numLemmingsExit();
			this.numLemmingsToWin = conf.numLemmingsToWin();
			this.obj = conf.reciveContenedor();
		}
	
	
	// GameStatus methods
	
	
	//METODOS QUE LLAMAN A LOS CONTADORES DEL GAMEOBJECTCONTAINER
	public int getCycle() {
		return ciclo++;
	}
	
	
	
	public int numLemmingsToWin() {
		// TODO Auto-generated method stub
		return numLemmingsToWin;
	}
	
	//METODOS PARA SABER PIERDES O GANAS EN EL JUEGO
	
	
	//MAS
	
		
	
	//GameModel methods
	
	
	//UPDATE DE GAME QUE LLAMA AL UPDATE DE GAMEOBJECTCONTAINER
	public void update() {
		obj.update();
	}
	
	//METODO QUE TE COMPRUEBA SI HA SALIDO DEL JUEGO
	public boolean isFinished() {
		boolean verdad = false;
		if((numLemmingsInBoard() == 0) || (exit) ){
			verdad = true;
		}
		return verdad;
	}
		
	public void exit() {
	    exit = true;
	}
	
	//METODO PARA EL REINCIO
		public boolean initGame(int nLevel) {
			boolean b = true;
			if(nLevel == 0) {
				initGame0();
			} 
			else if(nLevel == 1) {
				initGame1();	
			}
			else if(nLevel == 2) {
				initGame2();
			}
			else if (nLevel == -1) {
				reinicio(this.nLevel);
			} 
			else {
				b = false;
			}
			return b;
		}
		
	//METODO DE TE LLAMA AL METODO INITGAME QUE TE HACE EL REINICIO DEL JUEGO
	public boolean reinicio(int nLevel) {
		if(conf == FileGameConfiguration.NONE) {
			return initGame(nLevel);
		} else {
			try {
				load(this.fileName);
			} catch(GameLoadException e) {
				conf = FileGameConfiguration.NONE;
			}
			return true;
		}
	}
	
	//METODOS PARA LA INICIALIZACION DEL JUEGO
	public void initGame0() {
		ciclo = 0;
		lemmingsActivos = 0;
		lemmingsMuertos = 0;
		lemmingsGanan = 0;
		numLemmingsToWin = 2;
			
		this.obj = new GameObjectContainer();
		
		obj.add(new Lemming(new Position(2,3),3, true, this, new WalkerRole()));
		lemmingsActivos++;
		obj.add(new Lemming(new Position(0,8),3, true, this, new WalkerRole()));
		lemmingsActivos++;
		obj.add(new Lemming(new Position(9,0),3, true, this, new WalkerRole()));
		lemmingsActivos++;
	
		obj.add(new Wall(new Position(0,9), this));
		obj.add(new Wall(new Position(1,9), this));
		obj.add(new Wall(new Position(2,4), this));
		obj.add(new Wall(new Position(3,4), this));
		obj.add(new Wall(new Position(4,4), this));
		obj.add(new Wall(new Position(4,6), this));
		obj.add(new Wall(new Position(5,6), this));
		obj.add(new Wall(new Position(6,6), this));
		obj.add(new Wall(new Position(7,6), this));
		obj.add(new Wall(new Position(7,5), this));
		obj.add(new Wall(new Position(8,1), this));
		obj.add(new Wall(new Position(9,1), this));
		obj.add(new Wall(new Position(8,9), this));
		obj.add(new Wall(new Position(9,9), this));
		obj.add(new Wall(new Position(8,8), this));
		
		obj.add(new ExitDoor(new Position(4, 5), this));	
	}
	
	public void initGame1() {
		initGame0();
		
		obj.add(new Lemming(new Position(3,3),3, true, this, new WalkerRole()));
		lemmingsActivos++;
	}
	
	public void initGame2() {
		initGame1();
		
		obj.add(new Wall(new Position(3,5), this));
		
		obj.add(new MetalWall(new Position(3,6), this));
		
		obj.add(new Lemming(new Position(6,0),3, true, this, new WalkerRole()));
		lemmingsActivos++;
		obj.add(new Lemming(new Position(6,0),3, true, this, new ParachuterRole()));
		lemmingsActivos++;
	}
	
	
	//GameWorld methods (callbacks)
	
	
	//METODOS QUE LLAMAN A LAS FUNCIONES DEL GAMEOBJECTCONTAINER
	public boolean isSolid(Position p) {
		return obj.isSolid(p);
	}
				
	public boolean isExit(Position p) {
		return obj.isExit(p);
	}
	
	public boolean isInPosition(Position p) {
		return obj.isInPosition(p);
	}
	
	public boolean isAlive() {
		return obj.isAlive();
	}

	//METODO QUE LLAMA AL PINTA DE GAMEOBJECTCONTAINER 
	public String positionToString(int col, int fila) {
		return obj.positionToString(col, fila);
	}
	
	public int getLemmingsMuertos() {
		return lemmingsMuertos;
	}
	
	public int numLemmingsInBoard() {
		return lemmingsActivos;	
	}	

	public int numLemmingsDead() {
		return lemmingsMuertos;
	}
		
	public int numLemmingsExit() {
		return lemmingsGanan;
	}
	
	public void setLemmingsMuertos(int lemmingsMuertos) {
		this.lemmingsMuertos = lemmingsMuertos;
	}

	public int getLemmingsActivos() {
		return lemmingsActivos;
	}

	public void setLemmingsActivos(int lemmingsActivos) {
		this.lemmingsActivos = lemmingsActivos;
	}

	public int getLemmingsGanan() {
		return lemmingsGanan;
	}

	public void setLemmingsGanan(int lemmingsGanan) {
		this.lemmingsGanan = lemmingsGanan;
	}
	
	public boolean playerWins() {
		boolean gana = false;
		if(getLemmingsGanan() >= numLemmingsToWin() && numLemmingsInBoard() == 0) {
			gana = true;
		}
				
		return gana;
	}

	public boolean playerLooses() {
		boolean pierde = false;
		if(numLemmingsInBoard() == 0) {
			if(getLemmingsGanan()  < numLemmingsToWin()) {
				pierde = true;
			}
		}	
		return pierde;
	}
	
	
	public void lemmingExit() {
		this.lemmingsGanan++;
	    this.lemmingsActivos--;
	}

	public void lemmingDead() {
		this.lemmingsMuertos++;
		this.lemmingsActivos--;
	}
	
	public boolean setRole(Position pos, LemmingRole role) throws OffBoardException{
		if(!pos.isInBoard())
			throw new OffBoardException(Messages.OFF_WORLD_POSITION.formatted(Messages.POSITION.formatted(pos.getRow(), pos.getCol())));
		return obj.setRole(pos, role);
	}
	
	public boolean receiveInteractionsFrom(GameItem objeto) {
		return this.obj.checkInteractionsFrom(objeto);
		}

}