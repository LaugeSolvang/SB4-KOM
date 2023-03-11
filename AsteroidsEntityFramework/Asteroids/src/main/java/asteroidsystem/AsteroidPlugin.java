package asteroidsystem;

import asteroids.common.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        int numAsteroids = 5;
        for (int i = 0; i < numAsteroids; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        float radians = (float) Math.random() * 2 * 3.1415f;
        float speed = (float) Math.random() * 10f + 20f;

        asteroid.setRadius(20);
        asteroid.add(new MovingPart(0, speed, speed, 0));

        int height = gameData.getDisplayHeight();
        int width = gameData.getDisplayWidth();

        int x = (int) (Math.random() * (width - 100)) + 50; // Generate random x in range [50, width-50]
        int y = (int) (Math.random() * (height - 100)) + 50; // Generate random y in range [50, height-50]

        asteroid.add(new PositionPart(x, y, radians));
        asteroid.add(new LifePart(3));

        return asteroid;
    }


    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }
}
