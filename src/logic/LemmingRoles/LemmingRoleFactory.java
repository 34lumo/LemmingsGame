package logic.LemmingRoles;

import java.util.Arrays;
import java.util.List;

import exceptions.RoleParseException;
import tp1.view.Messages;

public class LemmingRoleFactory {
	
	private static final List<LemmingRole> availableRoles = Arrays.asList(
			new WalkerRole(),
			new ParachuterRole(),
			new DownCaverRole()
	);
	
	//igual que el commandGenerator
	public static LemmingRole parse(String word) throws RoleParseException {		
		for (LemmingRole r: availableRoles) {
			LemmingRole roleDevuelto = r.parse(word);
			if(roleDevuelto != null) {
				return roleDevuelto;
			}
		}
		throw new RoleParseException(Messages.UNKNOWN_ROLE.formatted(word));
	}
	
	public static String roleHelp() {
		StringBuilder LemmingRole = new StringBuilder();
		
		for (LemmingRole r: availableRoles) {
			LemmingRole.append(r.getHelp()).append("");
		}
		return LemmingRole.toString();
	}
	
	
}
