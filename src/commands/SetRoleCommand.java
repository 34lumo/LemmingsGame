package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.GameParseException;
import exceptions.OffBoardException;
import exceptions.RoleParseException;
import logic.LemmingRoles.LemmingRole;
import logic.LemmingRoles.LemmingRoleFactory;
import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.view.GameView;
import tp1.view.Messages;

public class SetRoleCommand extends Commands{
	private static final String NAME = Messages.COMMAND_SETROLE_NAME;
	private static final String SHORTCUT = Messages.COMMAND_SETROLE_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_SETROLE_DETAILS;
	private static final String HELP = Messages.COMMAND_SETROLE_HELP;
	private int row;
	private int col;
	private LemmingRole role;
	private Position pos;
	
	public SetRoleCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}
	
	public SetRoleCommand(LemmingRole role, Position pos) {
		super(NAME, SHORTCUT, DETAILS, HELP); 
		this.role = role;
		this.pos = pos;
		
	}
	
	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		try {
			boolean exito = game.setRole(this.pos, this.role);//te lanza excepcion
		if (exito) {
			game.update();
			view.showGame();
		} else {
			view.showError(Messages.LINE.formatted(Messages.NO_LEMMING_ADMITS_ROLE.formatted(Messages.POSITION.formatted(this.pos.getRow(),this.pos.getCol()), this.role.getName())));
		}
		
		} catch (OffBoardException obe) { 
			throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, obe);//recoge aqui  
		}
	}

	
	@Override
	public Commands parse(String[] commandWords) throws CommandParseException {
		if((!matchCommandName(commandWords[0]) && commandWords.length >= 1) || commandWords.length == 0) 
			return null;
			
		if(commandWords.length != 4) 
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			
		 try {
			LemmingRole role = LemmingRoleFactory.parse(commandWords[1]);
			char rowChar = commandWords[2].charAt(0);
			this.row = rowChar - 'A';
			this.col = Integer.parseInt(commandWords[3]) - 1;
			this.pos = new Position(col, row);
			return new SetRoleCommand(role, pos);
		 
			
	} catch (RoleParseException e) {
	 	throw new CommandParseException(Messages.INVALID_COMMAND_PARAMETERS, e);
	 	 }
		 catch (NumberFormatException e) {
			 throw new CommandParseException(Messages.INVALID_POSITION.formatted(Messages.POSITION.formatted(row, col)));
		 }
	} 
}			