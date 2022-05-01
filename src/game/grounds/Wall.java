package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

public class Wall extends HighGround{
	private String name;

	public Wall() {
		super('#', 80, 20);
		this.name = "Wall";
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	public String toString(){
		return name;
	}


}
