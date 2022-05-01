package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Goomba;
import game.actors.Koopa;
import game.actors.Player;
import game.actors.Toad;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Wall;
import game.grounds.trees.Mature;
import game.grounds.trees.Sapling;
import game.grounds.trees.Sprout;
import game.grounds.trees.Tree;
import game.items.Coin;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

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
			mario.addItemToInventory(new PowerStar());
			mario.addItemToInventory(new Wrench());

			// FIXME: the Goomba should be generated from the Tree
			gameMap.at(2, 1).addActor(new Koopa());
			gameMap.at(1,2).addActor(new Goomba());

			gameMap.at(42, 10).addItem(new Coin(100000));
			gameMap.at(41, 10).addItem(new PowerStar());
			gameMap.at(43, 10).addItem(new PowerStar());
			gameMap.at(44, 10).addItem(new SuperMushroom());
			gameMap.at(45, 10).addActor(new Toad());

			world.run();

	}
}
