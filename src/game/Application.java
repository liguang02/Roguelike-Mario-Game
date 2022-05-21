package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.allies.PrincessPeach;
import game.actors.allies.Toad;
import game.actors.enemies.Bowser;
import game.actors.enemies.FlameWard;
import game.grounds.*;
import game.grounds.fountains.HealthFountain;
import game.grounds.fountains.PowerFountain;
import game.grounds.trees.Mature;
import game.grounds.trees.Sapling;
import game.grounds.trees.Sprout;
import game.items.permanent.Coin;

import java.util.Arrays;
import java.util.List;


/**
 * The main class for the Mario World game.
 * @version 1.1.0
 * @author sthi0011, lcha0068, esea0003
 */
public class Application {
	/**
	 * The driver class of the game. It will create game map and add it into the world.
	 * Where player can be added into the world and other NPCs such as toad can be added into the game map.
	 * It will run the game by calling world.run()
	 * @param args main arguments, no need to fill just run.
	 */
	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(), new Lava(), new PowerFountain(), new HealthFountain(), new Fire());

			List<String> map = Arrays.asList(
				".#........................................##..........+.........................",
				".#..........+............+..................#...................................",
				"##..........................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_A___###++.............................",
				".......................................+#__H___###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");
			List<String> lavaZone = Arrays.asList(
				"...................LL.......................................................",
				".............................................##...........L..........+......",
				"..+.............................LL.............#............................",
				"................................LL..............#.................L.........",
				".................+................................L.........................",
				".................................................LL.........................",
				"................LLL..........LLL................##..........................",
				".........+......LLL..........L.L.....+........LL##..........................",
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
			String gameMapName = "Main Island";

			GameMap bossMap = new GameMap(groundFactory, lavaZone);
			world.addGameMap(bossMap);
			String bossMapName = "Lava Zone";

			Actor mario = new Player("Mario", 'm', 100000);
			world.addPlayer(mario, gameMap.at(43, 10));

			gameMap.at(43,10).addItem(new Coin(100000000));
			gameMap.at(44,10).addActor(new Toad());

			bossMap.at(63,7).addActor(new Bowser(bossMap.at(63,7)));
			bossMap.at(64,7).addActor(new PrincessPeach());

			bossMap.at(1,9).addActor(new FlameWard());
			bossMap.at(2,5).addActor(new FlameWard());
			bossMap.at(3,2).addActor(new FlameWard());
			bossMap.at(4,10).addActor(new FlameWard());

			gameMap.at(10,2).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			gameMap.at(67,5).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			gameMap.at(15,15).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			gameMap.at(43,17).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			gameMap.at(42,11).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			bossMap.at(0,0).setGround(new WarpPipe());

			world.run();
	}
}
