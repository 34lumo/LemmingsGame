package tp1.logic.gameobjects;

import exceptions.ObjectParseException;
import exceptions.OffBoardException;
import exceptions.RoleParseException;
import logic.LemmingRoles.LemmingRole;
import logic.LemmingRoles.LemmingRoleFactory;
import logic.LemmingRoles.WalkerRole;
import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.logic.GameStatus;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.util.MyStringUtils;
//import tp1.logic.lemmingRoles.WalkerRole; // Asegúrate de que esta importación esté presente
import tp1.view.Messages;

public class Lemming extends GameObject {
    private Direction dir = Direction.RIGHT;
    private int fuerzaCaida = 3;
    private int fuerza;
    private LemmingRole role;
    private boolean isSolid;

    //CONSTRUCTORA DEL LEMMING
    public Lemming(Position pos, int fuerzaCaida, boolean vivo, GameWorld game, LemmingRole role) {
        super(pos, game); 
        this.isAlive = vivo;
        this.fuerzaCaida = fuerzaCaida;
        this.role = role;// Asegúrate de que WalkerRole tenga un constructor por defecto
        this.fuerza = 0;
    }
    
    //CONSTRUCTOR PARA EL PARSE 
    public Lemming(GameWorld game, Position pos, int fuerza, LemmingRole role, Direction dir, boolean vivo) {
    	super(pos, game);
    	this.fuerza = fuerza;
    	this.fuerzaCaida = 3;
    	this.role = role;
    	this.dir = dir;
    	this.isAlive = vivo;
    }
    //METODO QUE ME HACE UNA COPIA DE MI LENMING
    
    Lemming() { }
    
    
    //CONSTRUCTOR DE LA COPIA
    public Lemming(Lemming lem) {
    	super(lem);
		this.fuerzaCaida = lem.fuerzaCaida;
		this.fuerza = lem.fuerza;
		this.dir = lem.dir;
		this.isAlive = lem.isAlive;
		this.isSolid = lem.isSolid(lem.getPos());
		this.role = lem.role.clone();
	}
    
    @Override
    public GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
		String[] words = MyStringUtils.splitWords(line);
		String nombre = words[1];
		Position posNueva;

		if (matchObjectName(nombre)) {
			try {
				String[] ws = words[0].replace("(", " ").replace(","," ").replace(")"," ").strip().split("( )+");
				int col = Integer.valueOf(ws[1]);
				int row = Integer.valueOf(ws[0]);
				posNueva = new Position(col,row);
				if(!posNueva.isInBoard()) {
					throw new OffBoardException(Messages.OBJECT_OFF_WORLD_POSITION.formatted(line));
				}
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				throw new ObjectParseException(Messages.INVALID_GAME_OBJECT.formatted(line));
			}
			catch (NumberFormatException e2) {
				throw new ObjectParseException(Messages.INVALID_POSITION.formatted(line));

			}
			Direction dir = Direction.StringToDir(words[2].toUpperCase());
			if(dir==null) {
				throw new ObjectParseException(Messages.UNKNOWN_OBJECT_DIRECTION.formatted(line));
			}
			if(dir != Direction.RIGHT && dir != Direction.LEFT) {
				throw new ObjectParseException(Messages.INVALID_DIRECTION.formatted(line));
			}

			int fuerza; 
			try {
				fuerza = Integer.valueOf(words[3]);
			}
			catch (NumberFormatException a) {
				throw new ObjectParseException(Messages.INVALID_HEIGHT.formatted(line));
			}
			LemmingRole role;
			try {
				role = LemmingRoleFactory.parse(words[4]);
			} catch (RoleParseException e) {
				throw new ObjectParseException(Messages.INVALID_LEMMING_ROLE.formatted(line));
			}
			if(role != null && dir != null) {
				return new Lemming(game, posNueva, fuerza, role, dir, true);
			}
			else 
				throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT.formatted(line));
		}	
		return null;
  }

    @Override
    public GameObject copy() {
    	return new Lemming(this);
    }
    
    /*public void setRol(LemmingRole role) {
    	this.role = role;
    	}*/ 
    
    public void disableRole() { //volver al WalkerRole 
    	this.role = new WalkerRole();
    } 
    
    public void update() {
    	checkInteractions();
		if(isAlive()) 
			role.play(this);
		
	}
    
   
	//METODO QUE NOS HACE TODO EL MOVIMIENTO DEL LEMMING
	public void walkOrFall() {
		if (this.estaEnLaPuerta()) {
			isAlive = false;
			game.lemmingExit();
		}
		else {
		    this.isFalling();
		    this.wasFalling();
		}
	}
	
	//METODO QUE LLAMA AL PLAY DE WALKERROLE
	
	
	
	@Override
	public String getIcon() {
		return role.getIcon(this);
	}

	@Override
	public boolean isAlive() {
		boolean si = false;
		if(this.getVivo()) {
			si = true;
		}
		return si;
	}
	
	
	//METODO QUE TE HACE EL PASO DEL LEMMIGN HACIA LA DERECHA O IZQUIERDA
	public void step() {
		this.pos = pos.move(dir);
	}
		
		// METODO QUE NOS CAMBIA DE DIBUJO EN CASO DE ENCONTRARNOS CON PARED O EL FINAL DEL TABLERO, Y NOS MUEVE
	public void vuelta() {
		Position posible = pos.move(dir);
			
		if((game.isSolid(posible) || !posible.isInBoard())) {
			this.dir = contrario(this.dir);	
		} 
		else{
			step();
		}
	}
		
	public boolean isInAir() {
	Position abajo = new Position(this.pos.getCol(), this.pos.getRow() + 1);
	boolean aire = false;
	if (!game.isSolid(abajo) && pos.isInBoard()) {
		aire = true;
		
	} else if (!pos.isInBoard()){
		this.setVivo(false);
		  game.lemmingDead();
	}
	
	
	return aire;
}
		
	//METODO QUE TE HACE LO QUE TE TIENE QUE HACER SI EL LEMMING ESTA CAYENDO
	public void isFalling() {
		if(isInAir()) {
		  if(this.pos.isInBoard()) {;
		  this.fuerza++;
		  Position hay = pos.move(this.dir.DOWN);
		  this.pos = hay;
		  } 
		}
		else {
		    land();
			vuelta();
		}	
	}
	
	public void fall() {
		this.fuerza++;
		  Position hay = pos.move(this.dir.DOWN);
		  this.pos = hay;
		  
		  if(!this.pos.isInBoard()) {
			  this.isAlive = false;
		  }
	}
	
	@Override
	public boolean setRole(LemmingRole role) {
		boolean si = false;
		if(!this.role.getName().equals(role.getName())) {
			this.role = role;
			si = true;
		}
		return si;
	}
	
		
		//METODO QUE COMPRUEBA SI EL LEMMING ESTABA CAYENDO, ES DECIR, SI YA HA TOCADO SUELO
		public boolean wasFalling() { 
			boolean si = false;
			if(this.fuerza > 0 && !this.isInAir()) {
				si = true;
			}
			return si;
		}
		
		//METODO QUE TE COMPRUEBA SI LA FUERZA QUE HA COJIDO EN LA CAIDA ES MAYOR QUE LA FUERZA DE CADA LEMMING PARA QUE MUERAN, QUE ES 3
		public void land() {
			if(this.getFuerza() >= this.getFuerzaCaida() ) {
				this.setVivo(false);
				game.lemmingDead();
			}
			this.fuerza = 0;
		}
		
		//METODO QUE COMPRUEBA SI LAS POSICION DEL LEMMING COINCIDE CON LA POSISION DE LA PUERTA
		public boolean estaEnLaPuerta() {
			boolean esta = false;
			if(game.isExit(this.pos)) {
				esta = true;
			}
			return esta;
		}
		
		//METODO QUE TE CAMBIA LA DIRECCION QUE TENIA EL LEMMING POR SU CONTRARIA
		public Direction contrario(Direction dir) {
			Direction r;
			if(dir == Direction.RIGHT) {
				r = Direction.LEFT;
			}
			else {
				r = Direction.RIGHT ;
			}
			return r;
		}
		
	    //METODOS GET Y SET DE LOS ATRIBUTOS DE LOS LEMMINGS
		
		public Position getPos() {
			return pos;
		}

		public void setPos(Position pos) {
			this.pos = pos;
		}

		public int getFuerzaCaida() {
			return fuerzaCaida;
		}

		public void setFuerzaCaida(int fuerzaCaida) {
			this.fuerzaCaida = fuerzaCaida;
		}

		public int getFuerza() {
			return fuerza;
		}

		public void setFuerza(int fuerza) {
			this.fuerza = fuerza;
		}

		public boolean getVivo() {
			return isAlive;
		}

		public void setVivo(boolean vivo) {
			this.isAlive = vivo;
		}
		
		public Direction getDir() {
			return dir;
		}

		public void setDir(Direction dir) {
			this.dir = dir;
		}
		
		@Override
		public boolean receiveInteraction(GameItem other) {
			return this.role.receiveInteraction(other, this);
	  }
		
		@Override
		public boolean interactWith(Wall wall) {
			return this.role.interactWith(wall, this);
		}
		
		@Override
		public boolean interactWith(MetalWall metal) {
			return this.role.interactWith(metal, this);
		}
		
		@Override
		public boolean interactWith(ExitDoor door) {
			return this.role.interactWith(door, this);
		}
		
		@Override
		public boolean interactWith(Lemming lemming) {
			return this.role.interactWith(lemming, this);
		}
		
		public boolean checkInteractions() {
			return game.receiveInteractionsFrom(this);
		}

		@Override
		public String getName() {
			return "Lemming";
		}
		
}