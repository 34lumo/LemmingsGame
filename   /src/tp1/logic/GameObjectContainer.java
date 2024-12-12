package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import logic.LemmingRoles.LemmingRole;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;

public class GameObjectContainer {
	private List<GameObject> objects;
	Direction dir;
    

	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object) {
		objects.add(object);
	}
	
	 //METODO QUE TE ELIMINA EL LEMMING DE LA LISTA
    public void eliminarObjeto(int i) {
    	this.objects.remove(i);
    }
    
    private void removeDead() {
		GameObject objeto;
		int i = objects.size()-1;
		while (i >= 0) {
			objeto = objects.get(i);
			if (!objeto.isAlive()) 
				eliminarObjeto(i);

			i--;
		}
	}
    
	public void update() {
	    for (int i = 0; i < objects.size(); i++) {
		   GameObject object = objects.get(i);
		   object.update();
		}
		removeDead();
	}
	
	public boolean isSolid(Position pos) {
		boolean hay = false;
		for(int i = 0; i < objects.size(); i++) {
			GameObject wall = objects.get(i);	
			if(wall.isInPosition(pos) && wall.isSolid(pos) && wall.isAlive()) {
				hay = true;
			}
		}
		return hay;
	}
		
	public boolean isExit(Position pos) {
		boolean hay = false;
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).isExit(pos)) {
				hay = true;
			}
		}
		return hay;
	}
	
	public boolean isAlive() {
		boolean hay = false;
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).isAlive()) {
				hay = true;
			}
		}
		return hay;
	}
	
	//METODO QUE TE DEVUELVE SI ESTA EN LA POSICION UN OBJETO
	public boolean isInPosition(Position pos) {
		boolean hay = false;
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).isInPosition(pos)) {
				hay = true;
			}
		}
		return hay;
	}
	
	//METODO DEL PINTA DE LOS LEMMINGS, PAREDES Y LA PUERTA
	public String positionToString(int col, int fila) {
		Position p = new Position(col, fila);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < objects.size(); i++){
			if(objects.get(i).isInPosition(p) && objects.get(i).isAlive()) {
				sb.append(objects.get(i).getIcon());
			}
		}
		return sb.toString();
	}

	//método para encontrar al lemming que cambia de rol
	public boolean setRole(Position pos, LemmingRole role){
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < objects.size()) {
				if(objects.get(i).isInPosition(pos) && objects.get(i).setRole(role)) {
					encontrado = true;
				}
				else 
				   i++;
		}
		
		return encontrado;
	}
	
	public boolean receiveInteractionsFrom(GameItem obj) {
		boolean si = false;
		for(GameObject objeto: objects) {
			if(objeto.receiveInteraction(obj)) {
				si = true;
			}
		}
		return si;
		}
	
	public boolean checkInteractionsFrom(GameItem other) {
		boolean exito = false;
		for(GameObject obj: objects) {
			if(obj.receiveInteraction(other)) {
				exito = true;
			}
		}
		return exito;	
	}
}