package tp1.view;

import tp1.util.MyStringUtils;

public class Messages {
	
	public static final String VERSION = "3.0";

	public static final String GAME_NAME = "Lemmings";

	public static final String USAGE = "Usage: %s [<level>]".formatted(GAME_NAME);

	public static final String WELCOME = String.format("%s %s%n", GAME_NAME, VERSION);

	public static final String LEVEL_NOT_A_NUMBER = "The level must be a number";
	public static final String INVALID_LEVEL_NUMBER = "Not valid level number";

	public static final String LEVEL_NOT_A_NUMBER_ERROR = String.format("%s: %%s", LEVEL_NOT_A_NUMBER);

	public static final String PROMPT = "Command > ";

	public static final String DEBUG = "[DEBUG] Executing: %s%n";
	public static final String ERROR = "[ERROR] Error: %s";
	
	// GAME STATUS
	public static final String NUMBER_OF_CYCLES = "Number of cycles: %s";
	public static final String NUM_LEMMINGS = "Lemmings in board: %s";
	public static final String DEAD_LEMMINGS = "Dead lemmings: %s";
	public static final String EXIT_LEMMINGS = "Lemmings exit door: %s ┃%s";

	// GAME END MESSAGE
	public static final String GAME_OVER = "Game over";
	public static final String PLAYER_QUITS = "Player leaves the game";
	public static final String PLAYER_WINS = "Player wins!";
	public static final String PLAYER_LOOSES = "Player lose...";

	// Position format
	public static final String POSITION = "(%s,%s)";

	// Other
	public static final String SPACE = " ";
	public static final String TAB = "   ";
	public static final String LINE_SEPARATOR = System.lineSeparator();
	public static final String LINE = "%s" + LINE_SEPARATOR;
	public static final String LINE_TAB = TAB + LINE;
	public static final String LINE_2TABS = TAB + LINE_TAB;

//Commands
	// Errores de factorías:
	public static final String UNKNOWN_COMMAND = "Unknown command: %s";
	public static final String UNKNOWN_GAME_OBJECT = "Unknown game object: \"%s\"";
	public static final String UNKNOWN_ROLE = "Unknown role: %s";
	// Errores de commandos:
	public static final String COMMAND_PARAMETERS_MISSING = "Missing parameters";
	public static final String COMMAND_INCORRECT_PARAMETER_NUMBER = "Incorrect parameter number";
	// Errores de ficheros:
	public static final String FILE_NOT_FOUND = "File not found: \"%s\"";
	public static final String READ_ERROR = "Undetermined error reading file \"%s\"";
	// Errores de parseo de otras cosas:
	public static final String INVALID_POSITION = "Invalid object position: \"%s\"";
	public static final String INVALID_COMMAND = "Invalid command: %s";
	public static final String INVALID_DIRECTION = "Invalid lemming direction: \"%s\"";
	public static final String INVALID_HEIGHT = "Invalid height: %s";
	public static final String INVALID_GAME_STATUS = "Invalid game status \"%s\"";
	public static final String INVALID_INIT_CONF = "Invalid init configuration because %s";
	public static final String ERROR_INVALID_INIT_CONF = "Unknown invalid init configuration";
	public static final String ERROR_PARSING_GAME_OBJECT = "Not a correct game object because: %s";
	public static final String ERROR_COMMAND_EXECUTE = "Command execute problem";
	public static final String NO_LEMMING_ADMITS_ROLE = "No lemming in position %s admits role %s";
	public static final String INVALID_COMMAND_PARAMETERS = "Invalid command parameters";
	public static final String OFF_WORLD_POSITION = "Position %s is off board";
	public static final String INVALID_GAME_OBJECT = "Invalid game object: \"%s\"";
	public static final String OBJECT_OFF_WORLD_POSITION = "Object position is off board: \"%s\"";
	public static final String INVALID_LINE_OBJECT = "Invalid entry \"%s\". Insufficient parameters";
	public static final String INVALID_LEMMING_ROLE = "Invalid lemming role: \"%s\"";
	public static final String INVALID_FILE_CONFIGURATION = "Invalid file \"%s\" configuration";


	
	public static final String HELP_AVAILABLE_COMMANDS = "Available commands:";
	public static final String COMMAND_HELP_TEXT = "%s: %s";



	public static final String[] HELP_LINES = new String[] { "Available commands:",
			"[s]et[R]ole ROLE ROW COL: sets the lemming in position (ROW,COL) to role ROLE",
		    "   [D]own [C]aver: Lemming caves downwards",
		    "   [P]arachuter: Lemming falls with a parachute",
		    "   [W]alker: Lemming that walks",
		   "[n]one | \"\": user does not perform any action",
		   "[r]eset [numLevel]: reset the game to initial configuration if not numLevel else load the numLevel map",
		   "[l]oad <fileName>: load the game configuration from text file <fileName>",
		   "[h]elp: print this help message",
		   "[e]xit: exits the game"
};
	
	public static final String HELP = String.join(LINE_SEPARATOR+"   ", HELP_LINES) + LINE_SEPARATOR;


	// UPDATE (NONE)
		public static final String COMMAND_UPDATE_NAME = "none";
		public static final String COMMAND_UPDATE_SHORTCUT = "n";
		public static final String COMMAND_UPDATE_DETAILS = "[n]one";
		public static final String COMMAND_UPDATE_HELP = "user does not perform any action";
		
		// EXIT
		public static final String COMMAND_EXIT_NAME = "exit";
		public static final String COMMAND_EXIT_SHORTCUT = "e";
		public static final String COMMAND_EXIT_DETAILS = "[e]xit";
		public static final String COMMAND_EXIT_HELP = "exits the game";
		
		// HELP
		public static final String COMMAND_HELP_NAME = "help";
		public static final String COMMAND_HELP_SHORTCUT = "h";
		public static final String COMMAND_HELP_DETAILS = "[h]elp";
		public static final String COMMAND_HELP_HELP = "print this help message";
		
		//LOAD
		public static final String COMMAND_LOAD_NAME = "load";
		public static final String COMMAND_LOAD_SHORTCUT = "l";
		public static final String COMMAND_LOAD_DETAILS = "[l]oad <fileName>";
		public static final String COMMAND_LOAD_HELP = "load the game configuration from text file <fileName>";
	//Roles
		public static final String WALKER_ROL_SYMBOL = "W";
		public static final String WALKER_ROL_NAME = "Walker";
		public static final String WALKER_ROL_HELP = "[W]alker: Lemming that walks";
		
		public static final String PARACHUTER_ROL_SYMBOL = "P";
		public static final String PARACHUTER_ROL_NAME = "Parachuter";
		public static final String PARACHUTER_ROL_HELP = "[P]arachuter: Lemming falls with a parachute";
		
		public static final String DOWN_CAVER_ROL_SYMBOL = "DC";
		public static final String DOWN_CAVER_ROL_NAME = "DownCaver";
		public static final String DOWN_CAVER_ROL_HELP = "[D]own [C]aver: Lemming caves downwards";

	//Symbols
		public static final String EMPTY = "";
		public static final String METALWALL = MyStringUtils.repeat("X",ConsoleView.CELL_SIZE);
		public static final String WALL = MyStringUtils.repeat("▓",ConsoleView.CELL_SIZE);
		public static final String EXIT_DOOR = "🚪";
		public static final String LEMMING_RIGHT = "B";
		public static final String LEMMING_LEFT = "ᗺ";
		public static final String LEMMING_PARACHUTE = "🪂";
		public static final String LEMMING_DOWN_CAVER = "´･ω･`";

		public static final String COMMAND_RESET_LEVEL_HELP = "reset the game to initial configuration";
		public static final String COMMAND_RESET_LEVEL_DETAILS = "[r]eset";
		public static final String COMMAND_RESET_SHORTCUT = "r";
		public static final String COMMAND_RESET_NAME = "reset";
		
		public static final String COMMAND_SETROLE_NAME = "setRole";
		public static final String COMMAND_SETROLE_SHORTCUT = "sr";
		public static final String COMMAND_SETROLE_DETAILS = "[s]et[R]ole ROLE ROW COL";
		public static final String COMMAND_SETROLE_HELP = "sets the lemming in position (ROW,COL) to role ROLE";
		public static final String NO_LEMMING_IN_POS = "SetRoleCommand error (Incorrect position or no object in that position admits that role)";

		public static final String UNKNOWN_OBJECT_DIRECTION = "Unknown object direction: \"%s\"";}