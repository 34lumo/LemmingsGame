package tp1.control;

import commands.Commands;
import exceptions.CommandException;
import commands.CommandGenerator;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private GameModel game;
	private GameView view;

	public Controller(GameModel game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 */
	public void run() {
		String[] words = null;

		view.showWelcome();
		
		view.showGame();
		while (!game.isFinished()) {
			try {
			words = view.getPrompt();
			Commands command = CommandGenerator.parse(words);
			if(command != null)
				command.execute(game, view);
			} catch (CommandException e) {
				view.showError(e.getMessage());
				Throwable cause = e.getCause();
				if (cause != null)
				 view.showError(cause.getMessage());
				view.showMessage(Messages.EMPTY);
			}
		}
		view.showEndMessage();
	}
}