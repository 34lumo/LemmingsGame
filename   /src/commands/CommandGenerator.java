package commands;

import java.util.Arrays;
import java.util.List;

import exceptions.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Commands> availableCommands = Arrays.asList(
			new UpdateCommand(),
			new ResetCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new SetRoleCommand(),
			new LoadCommand()
	);

	 public static Commands parse(String[] commandWords) throws CommandParseException {		
		for (Commands c: availableCommands) {
			Commands commandDevuelto = c.parse(commandWords);
			if(commandDevuelto != null) {
				return commandDevuelto;
			}
		}
		  throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		for (Commands c: availableCommands) {
			commands.append(c.helpText()).append("");
		}
		return commands.toString();
	}

}