package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.allies.PrincessPeach;
import game.actors.allies.Toad;
import game.actors.enemies.BigSlime;
import game.actors.enemies.Bowser;
import game.actors.enemies.FlyingKoopa;
import game.actors.enemies.Koopa;
import game.grounds.*;
import game.grounds.fountains.HealthFountain;
import game.grounds.fountains.PowerFountain;
import game.grounds.trees.Mature;
import game.grounds.trees.Sapling;
import game.grounds.trees.Sprout;
import game.items.consumable.FirePotion;
import game.items.consumable.PowerStar;
import game.items.consumable.SuperMushroom;
import game.items.permanent.DragonScaleBlade;
import game.items.permanent.Key;
import game.utilities.Status;


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

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(), new Lava(), new PowerFountain(), new HealthFountain());

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

			Actor mario = new Player("Mario", 'm', 500);
			mario.hurt(0);
			mario.addItemToInventory(new PowerStar());
			mario.addItemToInventory(new SuperMushroom());
			mario.addItemToInventory(new FirePotion());
			mario.addItemToInventory(new DragonScaleBlade());

			world.addPlayer(mario, gameMap.at(1, 8));
			gameMap.at(42,10).addActor(new Toad());
			gameMap.at(1,9).addActor(new BigSlime());
			gameMap.at(10,10).addActor(new BigSlime());
			gameMap.at(1,3).addActor(new Bowser(gameMap.at(1,3)));
			gameMap.at(0,0).addActor(new FlyingKoopa());
			gameMap.at(0,1).addActor(new Koopa());

			gameMap.at(45,10).addActor(new Toad());
			gameMap.at(12,10).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			gameMap.at(14,10).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			gameMap.at(60,12).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			gameMap.at(20,10).setGround(new WarpPipe(bossMap.at(0,0), bossMapName,gameMapName));
			bossMap.at(0,0).setGround(new WarpPipe());

			world.run();
	}
}
