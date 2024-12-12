package tp1.logic;

public interface GameConfiguration {

		public int getCycle();
		public int numLemmingsInBoard();
		public int numLemmingsDead();
		public int numLemmingsExit();
		public int numLemmingsToWin();
		
		public GameObjectContainer reciveContenedor(); 

	
}
