package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Koopa;
import game.actors.Player;
import game.actors.Toad;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Wall;
import game.grounds.trees.Mature;
import game.grounds.trees.Sapling;
import game.grounds.trees.Sprout;
import game.items.PowerStar;
import game.items.SuperMushroom;

/**
 * The main class for the Mario World game.
 * @version 1.1.0
 * @author sthi0011, lcha0068, esea0003
 */
public class Application {
	/**
	 * The driver class of the game. It will create game map and add it into the world.
	 * Where player can be added into the world and other npcs such as toad can be added into the game map.
	 * It will run the game by calling world.run()
	 * @param args main arguments, no need to fill just run.
	 */
	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor mario = new Player("mario", 'm', 100);
			world.addPlayer(mario, gameMap.at(1, 1));
			gameMap.at(2,1).addActor(new Koopa());
			gameMap.at(1,2).addActor(new Koopa());
			gameMap.at(45,10).addActor(new Toad());
		gameMap.at(2,1).addActor(new Koopa());
		gameMap.at(1,2).addActor(new Koopa());



		gameMap.at(43,10).addItem(new PowerStar());
			gameMap.at(43,11).addItem(new SuperMushroom());



		world.run();

	}
}
