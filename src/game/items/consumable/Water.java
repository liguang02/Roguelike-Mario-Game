package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;

public abstract class Water {

    private final String name;

    public Water(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public abstract void consume(Actor actor);

}
