package commands;

import exceptions.CommandParseException;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Commands {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}
	
	@Override
	 public Commands parse(String[] commandWords) throws CommandParseException {
	 	if (commandWords.length < 1 || !matchCommandName(commandWords[0]))
	 		return null;
	         
	 	if (commandWords.length == 1 && matchCommandName(commandWords[0]))
	 		return this;
	     
	 	throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	 }
}