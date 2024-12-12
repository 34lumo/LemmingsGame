package commands;

import exceptions.CommandParseException;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends Commands {
	private static final String NAME = Messages.COMMAND_RESET_NAME;
	private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_RESET_LEVEL_DETAILS;
	private static final String HELP = Messages.COMMAND_RESET_LEVEL_HELP;
	
	private static final int NO_LEVEL = -1;
	private int nLevel;
		
	public ResetCommand(int nLevel){
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.nLevel = nLevel;
	}
	
	protected ResetCommand() {
		this(NO_LEVEL);
	}
	
	  @Override
		public void execute(GameModel game, GameView view) {// throws CommandExecuteException {
			boolean exito = game.reinicio(this.nLevel);
			if (exito) 
				view.showGame();
			else 
				view.showError(Messages.INVALID_LEVEL_NUMBER);	
		}
	

	  @Override
	    public Commands parse(String[] commandWords) throws CommandParseException {
	        if(commandWords.length == 0 || !matchCommandName(commandWords[0])) {
	            return null;
	        }

	        if(commandWords.length > 2) {
	            throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	        }

	        try {
	            if (commandWords.length == 1) {
	                return new ResetCommand();
	            } else {
	                if (Integer.parseInt(commandWords[1].toUpperCase()) <= 2 && Integer.parseInt(commandWords[1].toUpperCase()) >= 0) {
	                    return new ResetCommand(Integer.parseInt(commandWords[1]));
	                } else {
	                    throw new CommandParseException(Messages.INVALID_LEVEL_NUMBER.formatted(commandWords[1]));
	                }
	            }
	        } catch (NumberFormatException e) {
	            throw new CommandParseException(Messages.INVALID_LEVEL_NUMBER.formatted(commandWords[1]));
	        }
	    }

}   
