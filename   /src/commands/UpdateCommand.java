package commands;

import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;

public class UpdateCommand extends NoParamsCommand {
	
	public static final String COMMAND_NONE_NAME = "none";
	public static final String COMMAND_NONE_SHORTCUT = "n";
	public static final String COMMAND_NONE_DETAILS = "[n]one | \"\"";
	public static final String COMMAND_NONE_HELP = "user does not perform any action";
	
	public UpdateCommand() {
		super(COMMAND_NONE_NAME, COMMAND_NONE_SHORTCUT, COMMAND_NONE_DETAILS, COMMAND_NONE_HELP); 
	}

	@Override
	public void execute(GameModel game, GameView view) {
		game.update();
		view.showGame();
		
		
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean matchCommandName(String name) {
		return super.matchCommandName(name) || name.equals("");
	}

}
