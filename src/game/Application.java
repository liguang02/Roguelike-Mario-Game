package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.Toad;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Wall;
import game.grounds.WarpPipe;
import game.grounds.trees.Mature;
import game.grounds.trees.Sapling;
import game.grounds.trees.Sprout;



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

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(), new WarpPipe());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#.......................C...........",
				"............................................#...................................",
				".......................C.....................##......................+..........",
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
				".......C............................................#...........................",
				"...................+.................................#..............C...........",
				"......................................................#.........................",
				".......................................................##.......................");
			List<String> lavaZone = Arrays.asList(
				"C..................LL.......................................................",
				".............................................##...........L..........+......",
				"..+.............................LL.............#............................",
				"................................LL..............#.................L.........",
				".................+................................L.........................",
				".................................................LL.........................",
				"................LLL..........LLL................##..........................",
				".........+......LLL..........L.L.....+........LL##.................B........",
				"................LLL..........LLL..............###...........................",
				"..............................................L.L...........................",
				"...............................................###..........................",
				"........................+.......LL...............LL......L......+...........",
				"................................LL.................#........................",
				"..+.................................................#.......................",
				"...................+.................................L......................",
				"........LL...........................................................L......"
			);

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

//			second game map
			GameMap bossMap = new GameMap(groundFactory, lavaZone);
			world.addGameMap(bossMap);

			Actor mario = new Player("Mario", 'm', 100);
//need to do the addPlayer for the new lava zone map but only do it with some condition (player jumps into the warp pipe)
			world.addPlayer(mario, gameMap.at(42, 10));
			gameMap.at(45,10).addActor(new Toad());

			world.run();

	}
}
