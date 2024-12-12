package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.GameLoadException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class LoadCommand extends NoParamsCommand {
	public static final String NAME = Messages.COMMAND_LOAD_NAME;
	public static final String SHORTCUT = Messages.COMMAND_LOAD_SHORTCUT;
	public static final String DETAILS = Messages.COMMAND_LOAD_DETAILS;
	public static final String HELP = Messages.COMMAND_LOAD_HELP;
	String filename;
	
	public LoadCommand(String filename) {
		super(NAME, SHORTCUT,DETAILS, HELP);
		this.filename = filename;
	}
	
	public LoadCommand() {
		super(NAME, SHORTCUT,DETAILS, HELP);
	}

	@Override 
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		try {
			game.load(this.filename);
			view.showGame();
		} catch (GameLoadException e) {
			throw new CommandExecuteException(Messages.INVALID_FILE_CONFIGURATION.formatted(filename), e);
		}
	}
	

	@Override
	public Commands parse(String[] parameter) throws CommandParseException {
		if(parameter.length == 0 || !matchCommandName(parameter[0])) {
			return null;
		} 
		if(parameter.length != 2) {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		return new LoadCommand(parameter[1]);
	}

	
}
